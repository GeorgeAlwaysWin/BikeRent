package bikerentUI.client;

import bikerentUI.PageController;
import bikerentUI.ScreenController;
import bikerentmodel.BikeRentModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ClientBookingController extends PageController {
    public final static ScreenController SC = ScreenController.getInstance();
    public final static HashMap <String, String> models = new HashMap<>();
    public final static HashMap <String, String> shops = new HashMap<>();

    @FXML
    private ChoiceBox<String> Shops;

    @FXML
    private ChoiceBox<String> Models;

    @FXML
    private Label Shop_count;

    @FXML
    private Tooltip model_tip;

    @FXML
    private Tooltip shop_tip;

    @FXML
    private DatePicker DateChoose;

    @FXML
    public void onBookButtonClick() {
        if (BikeRentModel.bookBike(Models.getValue(), Shops.getValue(), DateChoose.getValue())){
            try {
                SC.activate(ScreenController.Page.Client);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateModels(){
        Object[][] records = BikeRentModel.getAllModels();
        Models.getItems().clear();
        models.clear();
        for (Object[] record : records) {
            String description = "Type: " + record[1].toString() + ", gears: " + record[2].toString();
            models.put((String) record[0], description);
            Models.getItems().add((String) record[0]);
        }
    }

    public void updateShops(String model){
        Object[][] records = BikeRentModel.getShopsByModel(model);
        if (records != null) {
            Shops.setDisable(false);
            shops.clear();
            Shops.getItems().clear();
            Shop_count.setText("Found in " + String.valueOf(records.length) + " shops");
            for (Object[] record : records) {
                shops.put((String) record[0], (String) record[1]);
                Shops.getItems().add((String) record[0]);
            }
        } else {
            Shops.setDisable(true);
            Shop_count.setText("Out of stock");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateModels();

        Models.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old_item, String new_item) {
                updateShops(new_item);
                model_tip.setText(models.get(new_item));
            }
        });

        Shops.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old_item, String new_item) {
                shop_tip.setText(shops.get(new_item));
            }
        });

        DateChoose.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) < 0 );
            }
        });
    }

    public void onBackButtonClick(ActionEvent actionEvent) {
        try {
            SC.activate(ScreenController.Page.Client);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
