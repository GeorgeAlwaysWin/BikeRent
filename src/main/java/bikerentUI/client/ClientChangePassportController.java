package bikerentUI.client;

import bikerentUI.HelloApplication;
import bikerentUI.PageController;
import bikerentUI.ScreenController;
import bikerentmodel.BikeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static bikerentUI.client.ClientController.client;

public class ClientChangePassportController extends PageController {
    public static ScreenController SC;
    @FXML
    private Label Passport;
    @FXML
    private TextField NewPassport;
    @FXML
    private Label errorText;

    public void onApplyPassportButtonClick(ActionEvent actionEvent) {
        if (BikeModel.updateClientPassport(NewPassport.getText())){
            client = BikeModel.getClientInfo();
            Passport.setText((String) client.get("passport"));
            errorText.setText("");
        } else {
            errorText.setText("Enter 10 digits");
        };
    }

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
        SC = ScreenController.getInstance();
        Passport.setText((String) client.get("passport"));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
