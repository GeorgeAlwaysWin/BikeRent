package bikerentUI.client;

import bikerentUI.HelloApplication;
import bikerentUI.PageController;
import bikerentUI.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static bikerentUI.client.ClientController.client;

public class ClientChangeController extends PageController {
    public static ScreenController SC;

//    public HashMap <String, String> client;
    @FXML
    private Label Login;
    @FXML
    private Label Passport;
    @FXML
    private Label Address;
    public void onBackButtonClick(ActionEvent actionEvent) {
        try {
//            if (!SC.checkScreen("client")) {
//                SC.addScreen("client", FXMLLoader.load(getClass().getResource("client-view.fxml")));
//            }
            SC.activate(ScreenController.Page.Client);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onChangeAddressButtonClick(ActionEvent actionEvent) {
        try {
//            if (!SC.checkScreen("client-change-address")) {
//                SC.addScreen("client-change-address", FXMLLoader.load(getClass().getResource("client-change-address-view.fxml")));
//            }
            SC.activate(ScreenController.Page.ClientChangeAddress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onChangePassportButtonClick(ActionEvent actionEvent) {
        this.Address.setText(this.Address.getText());
        try {
//            if (!SC.checkScreen("client-change-passport")) {
//                SC.addScreen("client-change-passport", FXMLLoader.load(getClass().getResource("client-change-passport-view.fxml")));
//            }
            SC.activate(ScreenController.Page.ClientChangePassport);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onChangePasswordButtonClick(ActionEvent actionEvent) {
        try {
//            if (!SC.checkScreen("client-change-password")) {
//                SC.addScreen("client-change-password", FXMLLoader.load(getClass().getResource("client-change-password-view.fxml")));
//            }
            SC.activate(ScreenController.Page.ClientChangePassword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





    public void refresh(){
//        Pane screen = SC.getCurrentScreen();
        Login.setText((String) client.get("login"));
        Passport.setText((String) client.get("passport"));
        Address.setText((String) client.get("address"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SC = HelloApplication.SC;
        refresh();
    }

}
