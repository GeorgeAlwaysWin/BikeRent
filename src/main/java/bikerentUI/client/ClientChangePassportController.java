package bikerentUI.client;

import bikerentUI.PageController;
import bikerentUI.ScreenController;
import bikerentmodel.BikeRentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static bikerentUI.client.ClientController.client;

public class ClientChangePassportController extends PageController {
//    public final static ScreenController SC = ScreenController.getInstance();
    @FXML
    private Label Passport;
    @FXML
    private TextField NewPassport;
    @FXML
    private Label errorText;

    public void onApplyPassportButtonClick(ActionEvent actionEvent) {
        if (BikeRentModel.updateClientPassport(NewPassport.getText())){
            client = BikeRentModel.getClientInfo();
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
        Passport.setText((String) client.get("passport"));
    }

}
