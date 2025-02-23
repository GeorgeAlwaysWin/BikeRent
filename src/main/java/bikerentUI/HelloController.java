package bikerentUI;

import bikerentmodel.BikeRentModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController extends PageController {
    public final static ScreenController SC = ScreenController.getInstance();
    @FXML
    private Label errorText;
    @FXML
    private TextField log;
    @FXML
    private PasswordField pass;

    @FXML
    public void onLoginButtonClick() {
        if (BikeRentModel.Authorization(log.getText(), pass.getText(), false)){
            try {
                switch (BikeRentModel.getActiveUserRole()){
                    case "client" -> {
                        SC.activate(ScreenController.Page.Client);
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
        } else{
            errorText.setText("Invalid credentials");
        }
    }
    @FXML
    public void onRegButtonClick() {
        if (BikeRentModel.Authorization(log.getText(), pass.getText(), true)){
            try {
                SC.activate(ScreenController.Page.Client);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            errorText.setText("This login is already exist");
        }
    }

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        SC = HelloApplication.SC;
    }

}