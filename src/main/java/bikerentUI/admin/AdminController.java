package bikerentUI.admin;

import bikerentUI.PageController;
import bikerentUI.ScreenController;
import bikerentmodel.BikeRentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;

public class AdminController extends PageController {
//    public final static ScreenController SC = ScreenController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onUserButtonClick(ActionEvent actionEvent) {
        try {
            SC.activate(ScreenController.Page.AdminUserChange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onShopButtonClick(ActionEvent actionEvent) {
        try {
            SC.activate(ScreenController.Page.AdminShopChange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onChangeButtonClick(ActionEvent actionEvent) {
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
}
