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

public class WorkerModelChangeController extends PageController {
    public final static HashMap <String, String[]> models = new HashMap<>();

    @FXML
    private Label Status;

    @FXML
    private ChoiceBox<String> Models;

    @FXML
    private ChoiceBox<String> Actions;
    @FXML
    private ChoiceBox<String> Type;

    @FXML
    private TextField Model_name;

    @FXML
    private TextField Gears;

    public void updateActions(){
        Actions.getItems().add("Create");
        Actions.getItems().add("Update");
        Actions.getItems().add("Delete");
    }

    public void updateTypes(){
        Type.getItems().add("mountain");
        Type.getItems().add("city");
        Type.getItems().add("country");
    }

    public void updateModels(){
        Object[][] records = BikeRentModel.getAllModels();
        if (records != null) {
            models.clear();
            Models.getItems().clear();
            for (Object[] record : records) {
                models.put((String) record[0], new String[]{record[1].toString(), record[2].toString()});
                Models.getItems().add((String) record[0]);
            }
        }
    }

    public void clear_fields(){
        Model_name.setText("");
        Gears.setText("");
        Type.setValue("");
        Models.setValue("");
        Status.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateModels();
        updateActions();
        updateTypes();
        clear_fields();

        Actions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old_item, String new_item) {
                if (Objects.equals(old_item, new_item)){ return; }
                clear_fields();
                switch (new_item){
                    case "Create" -> {
                        Model_name.setDisable(false);
                        Type.setDisable(false);
                        Gears.setDisable(false);
                        Models.setDisable(true);
                    }
                    case "Update" -> {
                        Model_name.setDisable(false);
                        Type.setDisable(false);
                        Gears.setDisable(false);
                        Models.setDisable(false);
                    }
                    case  "Delete" -> {
                        Model_name.setDisable(true);
                        Type.setDisable(true);
                        Gears.setDisable(true);
                        Models.setDisable(false);
                    }
                }
            }
        });
        Models.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old_item, String new_item) {
                if (Objects.equals(old_item, new_item)){ return; }
                if (new_item==null | new_item==""){return;}
                Model_name.setText(new_item);
                String[] params = models.get(new_item);
                Gears.setText(params[1]);
                Type.setValue(params[0]);
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
                String name = Model_name.getText();
                String type = Type.getValue();
                String gears = Gears.getText();
                if ((Objects.equals(name, "")) | (Objects.equals(type, "")) | (Objects.equals(gears, ""))){
                    Status.setText("Enter model name, type and gears");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                if (Integer.valueOf(gears) > 40){
                    Status.setText("Max amount of gears is 40");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                BikeRentModel.createBikeModel(name, type, gears);
                updateModels();
                clear_fields();
                Status.setText("Model created");
                Status.setStyle("-fx-text-fill: green");
            }
            case "Update" -> {
                String name = Model_name.getText();
                String type = Type.getValue();
                String gears = Gears.getText();
                if ((Objects.equals(name, "")) | (Objects.equals(type, "")) | (Objects.equals(gears, ""))){
                    Status.setText("Name, type and gears can not be empty");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                if (Integer.valueOf(gears) > 40){
                    Status.setText("Max amount of gears is 40");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                BikeRentModel.updateBikeModel(Models.getValue(), name, type, gears);
                updateModels();
                Models.setValue(name);
                Status.setText("Model updated");
                Status.setStyle("-fx-text-fill: green");
            }
            case  "Delete" -> {
                String name = Models.getValue();
                if (!Objects.equals(name, "")) {
                    if (BikeRentModel.deleteBikeModel(name)) {
                        models.remove(name);
                        Models.getItems().remove(name);
                        clear_fields();
                        Status.setText("Model deleted");
                        Status.setStyle("-fx-text-fill: green");
                    } else {
                        Status.setText("There are bikes with this model");
                        Status.setStyle("-fx-text-fill: red");
                    }
                } else {
                    Status.setText("Choose a Model");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
            }
        }
    }
}
