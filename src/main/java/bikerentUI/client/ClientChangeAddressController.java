package bikerentUI.client;

import bikerentUI.HelloApplication;
import bikerentUI.PageController;
import bikerentUI.ScreenController;
import bikerentmodel.BikeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import static bikerentUI.client.ClientController.client;

public class ClientChangeAddressController extends PageController {
    public static ScreenController SC;

    public void onApplyAddressButtonClick(ActionEvent actionEvent) {
        if (BikeModel.updateClientAddress(NewAddress.getText())){
            client = BikeModel.getClientInfo();
            Address.setText((String) client.get("address"));
        };
    }

    @FXML
    private Label Address;
    @FXML
    private TextField NewAddress;

    @FXML
    public void onChangeButtonClick() {
        try {
//            if (!SC.checkScreen("client-change")) {
//                SC.addScreen("client-change", FXMLLoader.load(getClass().getResource("client-change-view.fxml")));
//            }
            SC.activate(ScreenController.Page.ClientChange);
//            SC.notify("Scene","CCAC","CCC");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SC = HelloApplication.SC;
        Address.setText((String) client.get("address"));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
