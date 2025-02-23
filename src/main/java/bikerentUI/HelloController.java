package bikerentUI;

import bikerentmodel.BikeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.beans.PropertyChangeEvent;
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
        if (BikeModel.Authorization(log.getText(), pass.getText(), false)){
            try {
                SC.activate(ScreenController.Page.Client);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else{
            errorText.setText("Invalid credentials");
        }
    }
    @FXML
    public void onRegButtonClick() {
        if (BikeModel.Authorization(log.getText(), pass.getText(), true)){
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}