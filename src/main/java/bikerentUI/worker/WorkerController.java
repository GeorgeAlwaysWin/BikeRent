package bikerentUI.worker;

import bikerentUI.PageController;
import bikerentUI.ScreenController;
import bikerentmodel.BikeRentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkerController extends PageController {
    @FXML
    private Label Login;

    public void onBookingButtonClick(ActionEvent actionEvent) {
        try {
            SC.activate(ScreenController.Page.WorkerBookingControl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onBikeButtonClick(ActionEvent actionEvent) {
        try {
            SC.activate(ScreenController.Page.WorkerBikeChange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onModelsButtonClick(ActionEvent actionEvent) {
        try {
            SC.activate(ScreenController.Page.WorkerModelChange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onChangeShopButtonClick(ActionEvent actionEvent) {
        try {
            SC.activate(ScreenController.Page.WorkerShop);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onChangePasswordButtonClick(ActionEvent actionEvent) {
        try {
            SC.activate(ScreenController.Page.UserChangePassword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onLogOutButtonClick(ActionEvent actionEvent) {
        BikeRentModel.LogOut();
        try {
            SC.activate(ScreenController.Page.Hello);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Login.setText(BikeRentModel.getActiveUser());
    }
}
