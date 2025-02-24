package bikerentUI;

import bikerentmodel.BikeRentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserChangePasswordController extends PageController {
//    public final static ScreenController SC = ScreenController.getInstance();
    @FXML
    private PasswordField OldPassword;
    @FXML
    private TextField NewPassword;
    @FXML
    private Label errorText;

    public void onApplyPasswordButtonClick(ActionEvent actionEvent) {
        if (BikeRentModel.updateActiveUserPassword(OldPassword.getText(), NewPassword.getText())){
            OldPassword.setText("");
            NewPassword.setText("");
            errorText.setText("Password changed");
            errorText.setStyle("-fx-text-fill: green");
        } else {
            errorText.setStyle("-fx-text-fill: red");
            errorText.setText("Wrong password");
        };
    }

    @FXML
    public void onChangeButtonClick() {
        try {
            switch (BikeRentModel.getActiveUserRole()){
                case "client" -> {
                    SC.activate(ScreenController.Page.ClientChange);
                }
                case "admin" -> {
                    SC.activate(ScreenController.Page.Admin);
                }
                case "worker" -> {
                    SC.activate(ScreenController.Page.Worker);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
