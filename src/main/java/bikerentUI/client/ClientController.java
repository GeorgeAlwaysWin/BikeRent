package bikerentUI.client;

import bikerentUI.HelloApplication;
import bikerentUI.PageController;
import bikerentUI.ScreenController;
import bikerentmodel.BikeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ClientController extends PageController {
    public static ScreenController SC;
    public static HashMap <String, String> client;

    @FXML
    private Label Login;

    @FXML
    public void onChangeButtonClick() {
        try {
            SC.activate(ScreenController.Page.ClientChange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onBookButtonClick() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SC = HelloApplication.SC;
        client = BikeModel.getClientInfo();
        Login.setText("Welcome, " + (String) client.get("login"));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void onLogOutButtonClick(ActionEvent actionEvent) {
        BikeModel.LogOut();
        try {
            SC.activate(ScreenController.Page.Hello);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
