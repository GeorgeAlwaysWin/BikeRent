package bikerentUI.worker;

import bikerentUI.PageController;
import bikerentUI.ScreenController;
import bikerentmodel.BikeRentModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class WorkerShopController extends PageController {
//    public final static HashMap <String, String> shops = new HashMap<>();

    @FXML
    private Label Status;

    @FXML
    private ChoiceBox<String> Shops;


    public void updateShops(){
        Object[][] records = BikeRentModel.getAllShops();
        if (records != null) {
            Shops.getItems().clear();
            for (Object[] record : records) {
                Shops.getItems().add((String) record[0]);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Shops.setValue("");
        updateShops();
        String shop = BikeRentModel.getShop();
        if (shop != null){
            Shops.setValue(shop);
        }
    }

    public void onBackButtonClick(ActionEvent actionEvent) {
        try {
            SC.activate(ScreenController.Page.Worker);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onApplyButtonClick(ActionEvent actionEvent) {
        String new_shop = Shops.getValue();
        if (new_shop == ""){
            Status.setText("Choose shop");
            Status.setStyle("-fx-text-fill: red");
            return;
        }
        String shop = BikeRentModel.getShop();
        if (shop != null){
            if (BikeRentModel.updateWorkerShop(new_shop)){
                Status.setText("Shop changed");
                Status.setStyle("-fx-text-fill: green");
            } else {
                Status.setText("Update error");
                Status.setStyle("-fx-text-fill: red");
            }
        } else {
            if (BikeRentModel.setWorkerShop(new_shop)){
                Status.setText("Shop changed");
                Status.setStyle("-fx-text-fill: green");
            } else {
                Status.setText("Update error");
                Status.setStyle("-fx-text-fill: red");
            }
        }
    }
}
