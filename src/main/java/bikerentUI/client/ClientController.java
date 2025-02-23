package bikerentUI.client;

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

public class ClientController extends PageController {
    public static HashMap <String, String> client;
    @FXML
    private Button Cancel_booking;
    @FXML
    private Label Show_booking;
    @FXML
    private Button Book_bike;
    @FXML
    private Label Login;

    @FXML
    public void onChangeButtonClick() {
        try {
            SC.activate(ScreenController.Page.ClientChange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onBookButtonClick() {
        try {
            SC.activate(ScreenController.Page.ClientBooking);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = BikeRentModel.getClientInfo();
        Object[] booking = BikeRentModel.getActiveClientBooking();
        if (booking != null){
            if (BikeRentModel.checkRentByBooking((long) booking[5])){
                Book_bike.setDisable(false);
                Cancel_booking.setDisable(false);
                Show_booking.setText(String.format("The '%s' bike is rented in %s\n at %s\n for %s days\n Return bike with code: %07d",
                        (String) booking[1],
                        (String) booking[2],
                        BikeRentModel.getAddressByShopName((String) booking[2]),
                        Period.between(BikeRentModel.dateToLocalDate((Date) booking[3]), LocalDate.now()).getDays(),
                        (long) booking[5]
                ));
            } else {
                LocalDate book_day = BikeRentModel.dateToLocalDate((Date) booking[3]);
                if (book_day.isBefore(LocalDate.now())){
                    if (BikeRentModel.cancelActiveClientBooking()) {
                        Book_bike.setDisable(false);
                        Cancel_booking.setDisable(true);
                        Show_booking.setText("Book bike with a button below");
                    }
                } else {
                    Book_bike.setDisable(true);
                    Cancel_booking.setDisable(false);
                    Show_booking.setText(String.format("The '%s' bike is booked in %s\n at %s\n on %s\n Code: %07d",
                            (String) booking[1],
                            (String) booking[2],
                            BikeRentModel.getAddressByShopName((String) booking[2]),
                            book_day.toString(),
                            (long) booking[5]
                    ));
                }
            }
        }
        Login.setText("Welcome, " + (String) client.get("login"));
    }

    public void onLogOutButtonClick(ActionEvent actionEvent) {
        BikeRentModel.LogOut();
        try {
            SC.activate(ScreenController.Page.Hello);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onCancelBookingButtonClick(ActionEvent actionEvent) {
        if (BikeRentModel.cancelActiveClientBooking()) {
            Book_bike.setDisable(false);
            Cancel_booking.setDisable(true);
            Show_booking.setText("Book bike with a button below");
        }
    }
}
