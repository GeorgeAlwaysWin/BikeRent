package bikerentUI.client;

import bikerentUI.PageController;
import bikerentUI.ScreenController;
import bikerentmodel.BikeRentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientChangePasswordController extends PageController {
    public final static ScreenController SC = ScreenController.getInstance();
    @FXML
    private PasswordField OldPassword;
    @FXML
    private TextField NewPassword;
    @FXML
    private Label errorText;

    public void onApplyPasswordButtonClick(ActionEvent actionEvent) {
        if (BikeRentModel.updatePassword(OldPassword.getText(), NewPassword.getText())){
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
            SC.activate(ScreenController.Page.ClientChange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
