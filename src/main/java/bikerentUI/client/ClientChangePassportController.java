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
import java.util.Objects;
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
            String passport = (String) client.get("passport");
            Passport.setText("Current passport: " + passport + "\nEnter new passport:");
            errorText.setText("");
        } else {
            errorText.setText("Enter 10 digits");
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
        String passport = (String) client.get("passport");
        if (!Objects.equals(passport, "")) {
            Passport.setText("Current passport: " + passport + "\nEnter new passport:");
        }
    }

}
