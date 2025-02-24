package bikerentUI.admin;

import bikerentUI.PageController;
import bikerentUI.ScreenController;
import bikerentmodel.BikeRentModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminUserChangeController extends PageController {

    @FXML
    private TextField Password;

    @FXML
    private TextField Username;

    @FXML
    private Button SearchButton;

    @FXML
    private Label Status;

    @FXML
    private ChoiceBox<String> Roles;

    @FXML
    private ChoiceBox<String> Actions;


    public void updateActions(){
        Actions.getItems().add("Create");
        Actions.getItems().add("Update role");
        Actions.getItems().add("Update password");
        Actions.getItems().add("Delete");
    }

    public void updateRoles(){
        Roles.getItems().add("client");
        Roles.getItems().add("worker");
        Roles.getItems().add("admin");
    }

//    public void clear_fields(){
//        Username.setText("");
//        Password.setText("");
//        Roles.setValue("");
//        Status.setText("");
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateRoles();
        updateActions();
//        clear_fields();

        Actions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old_item, String new_item) {
                if (Objects.equals(old_item, new_item)){ return; }
                Username.setDisable(false);
                switch (new_item){
                    case "Create" -> {
                        Roles.setDisable(false);
                        Password.setDisable(false);
                        SearchButton.setDisable(true);
                    }
                    case "Update role" -> {
                        Roles.setDisable(false);
                        Password.setDisable(true);
                        Password.setText("");
                        SearchButton.setDisable(false);
                    }
                    case "Update password" -> {
                        Roles.setDisable(true);
                        Password.setDisable(false);
                        SearchButton.setDisable(false);
                    }
                    case  "Delete" -> {
                        Roles.setDisable(true);
                        Password.setDisable(true);
                        Password.setText("");
                        SearchButton.setDisable(false);
                    }
                }
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

    public boolean searchUser(boolean updateUI){
        String log = Username.getText();
        if (Objects.equals(log, "")){
            if (updateUI) {
                Status.setText("Enter user name");
                Status.setStyle("-fx-text-fill: red");
            }
            return false;
        }
        Object[] user = BikeRentModel.getUser(log);
        if (user==null){
            if (updateUI) {
                Status.setText("User not found");
                Status.setStyle("-fx-text-fill: red");
            }
            return false;
        }
        if (updateUI) {
            Roles.setValue((String) user[1]);
            Status.setText("User found");
            Status.setStyle("-fx-text-fill: green");
        }
        return true;
    }

    public void onApplyButtonClick(ActionEvent actionEvent) {
        if (Actions.getValue() == null){
            return;
        }
        boolean isUserExists = searchUser(false);
        switch (Actions.getValue()){
            case "Create" -> {
                if (isUserExists){
                    Status.setText("User already exists");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                String name = Username.getText();
                String password = Password.getText();
                String role = Roles.getValue();
                if ((Objects.equals(name, "")) | (Objects.equals(role, ""))){
                    Status.setText("Enter user name and role");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                if (BikeRentModel.regHashUser(name, password, role)) {
                    Password.setText("");
                    Status.setText("User created");
                    Status.setStyle("-fx-text-fill: green");
                } else {
                    Status.setText("Registration error");
                    Status.setStyle("-fx-text-fill: red");
                }
            }
            case "Update role" -> {
                if (!isUserExists){
                    Status.setText("User not found");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                String name = Username.getText();
                String role = Roles.getValue();
                if ((Objects.equals(name, "")) | (Objects.equals(role, ""))){
                    Status.setText("Enter user name and role");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                BikeRentModel.updateUserRole(Username.getText(), Roles.getValue());

                Status.setText("Role updated");
                Status.setStyle("-fx-text-fill: green");
            }
            case "Update password" -> {
                if (!isUserExists){
                    Status.setText("User not found");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                String name = Username.getText();
                String password = Password.getText();
                if (Objects.equals(name, "")){
                    Status.setText("Enter user name");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                BikeRentModel.updatePassword(Username.getText(), BikeRentModel.stringToRole(Roles.getValue()), Password.getText());

                Status.setText("Password updated");
                Status.setStyle("-fx-text-fill: green");
            }
            case  "Delete" -> {
                if (!isUserExists){
                    Status.setText("User not found");
                    Status.setStyle("-fx-text-fill: red");
                    return;
                }
                String name = Username.getText();
                if (!Objects.equals(name, "")) {
                    BikeRentModel.deleteUser(name);
                    Status.setText("User deleted");
                    Status.setStyle("-fx-text-fill: green");
                } else {
                    Status.setText("Enter a valid name");
                    Status.setStyle("-fx-text-fill: red");
                }
            }
        }
    }

    public void onSearchButtonClick(ActionEvent actionEvent) {
        searchUser(true);
    }
}
