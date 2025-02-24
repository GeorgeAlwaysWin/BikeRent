package bikerentUI.admin;

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
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminShopChangeController extends PageController {
//    public final static HashMap <String, String> models = new HashMap<>();
    public final static HashMap <String, String> shops = new HashMap<>();

    @FXML
    private Label Status;

    @FXML
    private ChoiceBox<String> Shops;

    @FXML
    private ChoiceBox<String> Actions;

    @FXML
    private TextField Address;

    @FXML
    private TextField Shop_name;

    public void updateActions(){
        Actions.getItems().add("Create");
        Actions.getItems().add("Update");
        Actions.getItems().add("Delete");
    }

    public void updateShops(){
        Object[][] records = BikeRentModel.getAllShops();
        if (records != null) {
            shops.clear();
            Shops.getItems().clear();
            for (Object[] record : records) {
                shops.put((String) record[0], (String) record[1]);
                Shops.getItems().add((String) record[0]);
            }
        }
    }

    public void clear_fields(){
        Shop_name.setText("");
        Address.setText("");
        Shops.setValue("");
        Status.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateShops();
        updateActions();
        clear_fields();

        Actions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old_item, String new_item) {
                if (Objects.equals(old_item, new_item)){ return; }
                clear_fields();
                switch (new_item){
                    case "Create" -> {
                        Shop_name.setDisable(false);
                        Address.setDisable(false);
                        Shops.setDisable(true);
                    }
                    case "Update" -> {
                        Shop_name.setDisable(false);
                        Address.setDisable(false);
                        Shops.setDisable(false);
                    }
                    case  "Delete" -> {
                        Shop_name.setDisable(true);
                        Address.setDisable(true);
                        Shops.setDisable(false);
                    }
                }
            }
        });
        Shops.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old_item, String new_item) {
                if (Objects.equals(old_item, new_item)){ return; }
                Shop_name.setText(new_item);
                Address.setText(shops.get(new_item));
            }
        });
    }

    public void onBackButtonClick(ActionEvent actionEvent) {
        try {
            SC.activate(ScreenController.Page.Admin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onApplyButtonClick(ActionEvent actionEvent) {
        if (Actions.getValue() == null){
            return;
        }
        switch (Actions.getValue()){
            case "Create" -> {
                String name = Shop_name.getText();
                String address = Address.getText();
                if ((Objects.equals(name, "")) | (Objects.equals(address, ""))){
                    Status.setText("Enter shop name and address");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                BikeRentModel.createShop(name, address);
                updateShops();
                clear_fields();
                Status.setText("Shop created");
                Status.setStyle("-fx-text-fill: green");
            }
            case "Update" -> {
                String name = Shop_name.getText();
                String address = Address.getText();
                if ((Objects.equals(name, "")) | (Objects.equals(address, ""))){
                    Status.setText("Name and address can not be empty");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                BikeRentModel.updateShop(Shops.getValue(), name, address);
                updateShops();
                Shops.setValue(name);
                Status.setText("Shop updated");
                Status.setStyle("-fx-text-fill: green");
            }
            case  "Delete" -> {
                String name = Shops.getValue();
                if (!Objects.equals(name, "")) {
                    BikeRentModel.deleteShop(name);
                    shops.remove(name);
                    Shops.getItems().remove(name);
                    clear_fields();
                    Status.setText("Shop deleted");
                    Status.setStyle("-fx-text-fill: green");
                } else {
                    Status.setText("Choose a shop");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
            }
        }
    }
}
