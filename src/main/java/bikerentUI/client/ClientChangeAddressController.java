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

public class ClientChangeAddressController extends PageController {
    public static ScreenController SC;

    public void onApplyAddressButtonClick(ActionEvent actionEvent) {
        if (BikeRentModel.updateClientAddress(NewAddress.getText())){
            client = BikeRentModel.getClientInfo();
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
            SC.activate(ScreenController.Page.ClientChange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SC = ScreenController.getInstance();
        Address.setText((String) client.get("address"));
    }

}
