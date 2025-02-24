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

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class WorkerBikeChangeController extends PageController {
    public final static HashMap <String, String> bikes = new HashMap<>();

    @FXML
    private Label Status;

    @FXML
    private ChoiceBox<String> Models;

    @FXML
    private ChoiceBox<String> Actions;
    @FXML
    private ChoiceBox<String> Bikes;

    public void updateActions(){
        Actions.getItems().add("Create");
        Actions.getItems().add("Delete");
    }

    public void updateBikes(){
        Object[][] records = BikeRentModel.getNotRentedBikesFromShop();
        if (records != null) {
            bikes.clear();
            Bikes.getItems().clear();
            for (Object[] record : records) {
                bikes.put(record[0].toString(), record[1].toString());
                Bikes.getItems().add(record[0].toString());
            }
        }
    }

    public void updateModels(){
        Object[][] records = BikeRentModel.getAllModels();
        if (records != null) {
            Models.getItems().clear();
            for (Object[] record : records) {
                Models.getItems().add((String) record[0]);
            }
        }
    }

    public void clear_fields(){
        Bikes.setValue("");
        Models.setValue("");
        Status.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateBikes();
        updateActions();
        updateModels();
        clear_fields();

        Actions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old_item, String new_item) {
                if (Objects.equals(old_item, new_item)){ return; }
                clear_fields();
                switch (new_item){
                    case "Create" -> {
                        Bikes.setDisable(true);
                        Models.setDisable(false);
                    }
                    case  "Delete" -> {
                        Bikes.setDisable(false);
                        Models.setDisable(true);
                    }
                }
            }
        });
        Bikes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old_item, String new_item) {
                if (Objects.equals(old_item, new_item)){ return; }
                if (new_item==null | new_item==""){return;}
                Models.setValue(bikes.get(new_item));
            }
        });
    }

    public void onBackButtonClick(ActionEvent actionEvent) {
        try {
            SC.activate(ScreenController.Page.Worker);
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
                String model = Models.getValue();
                if (Objects.equals(model, "")){
                    Status.setText("Choose bike model");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                BikeRentModel.createBike(model);
                updateBikes();
                clear_fields();
                Status.setText("Model created");
                Status.setStyle("-fx-text-fill: green");
            }
            case  "Delete" -> {
                String id = Bikes.getValue();
                if (!Objects.equals(id, "")) {
                    if (BikeRentModel.deleteBike(id)) {
                        bikes.remove(id);
                        Bikes.getItems().remove(id);
                        clear_fields();
                        Status.setText("Bike deleted");
                        Status.setStyle("-fx-text-fill: green");
                    } else {
                        Status.setText("Error");
                        Status.setStyle("-fx-text-fill: red");
                    }
                } else {
                    Status.setText("Choose bike");
                    Status.setStyle("-fx-text-fill: red");
                }
            }
        }
    }
}
