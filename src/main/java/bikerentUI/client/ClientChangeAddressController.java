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

public class ClientChangeAddressController extends PageController {

    public void onApplyAddressButtonClick(ActionEvent actionEvent) {
        if (BikeRentModel.updateClientAddress(NewAddress.getText())){
            client = BikeRentModel.getClientInfo();
            String address = (String) client.get("address");
            Address.setText("Current address: " + address + "\nEnter new address:");
        };
    }

    @FXML
    private Label Address;
    @FXML
    private TextField NewAddress;

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
        String address = (String) client.get("address");
        if (!Objects.equals(address, "")) {
            Address.setText("Current address: " + address + "\nEnter new address:");
        }
    }

}
