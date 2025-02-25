package bikerentUI.worker;

import bikerentUI.PageController;
import bikerentUI.ScreenController;
import bikerentmodel.BikeRentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static bikerentmodel.BikeRentModel.getBookingById;

public class WorkerBookingControlController extends PageController {
    HashMap <String, Object> booking;
//    private long booking_id;

    @FXML
    private Button ApplyButton;

    @FXML
    private TextField Booking;

    @FXML
    private Label Rent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onBackButtonClick(ActionEvent actionEvent) {
        try {
            SC.activate(ScreenController.Page.Worker);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onApplyButtonClick(ActionEvent actionEvent) {
        if (Objects.equals(ApplyButton.getText(), "End rent")){
            LocalDateTime time = LocalDateTime.now();
            BikeRentModel.setBookingEnd((Long) booking.get("book_id"), time);
            BikeRentModel.endRent((Long) booking.get("book_id"));
            Rent.setText("Rent ended");
            Rent.setStyle("-fx-text-fill: green");
            ApplyButton.setDisable(true);
            ApplyButton.setText("Unavailable");
        } else if (Objects.equals(ApplyButton.getText(), "Start rent")){
            Long bike_id = BikeRentModel.getBike((String) booking.get("bike_model"));
            if (bike_id == null){
                Rent.setText("Out of stock");
                Rent.setStyle("-fx-text-fill: red");
                ApplyButton.setDisable(true);
                ApplyButton.setText("Unavailable");
                return;
            }
            if (BikeRentModel.startRent((Long) booking.get("book_id"), bike_id)){
                Rent.setText(String.format("Rent started.\n Bike id: %07d", bike_id));
                Rent.setStyle("-fx-text-fill: green");
                ApplyButton.setDisable(true);
                ApplyButton.setText("Unavailable");
            }
        }
    }

    public void searchBooking(){
        String book_id = Booking.getText();
        if (!Pattern.matches("\\d{7}", book_id)) {
            Rent.setText("Enter 7 digits");
            Rent.setStyle("-fx-text-fill: red");
            return;
        }
        booking = BikeRentModel.getBookingById(book_id);
        if (booking == null){
            Rent.setText("Booking not found");
            Rent.setStyle("-fx-text-fill: red");
            return;
        }
        if (booking.get("shop_name")!=BikeRentModel.getShop()){
            Rent.setText("Bike booked in other shop: " + booking.get("shop_name"));
            Rent.setStyle("-fx-text-fill: red");
            return;
        }
        if (BikeRentModel.checkRentByBooking((Long) booking.get("book_id"))){
            Rent.setText(String.format("Bike is currently rented.\nCode: %07d",(Long) booking.get("book_id")));
            Rent.setStyle("-fx-text-fill: green");
            ApplyButton.setText("End rent");
            ApplyButton.setDisable(false);
//            booking_id = (Long) booking.get("book_id");
            return;
        }

        LocalDate book_day = BikeRentModel.dateToLocalDate((Date) booking.get("start_date"));
        if (!book_day.isEqual(LocalDate.now())){
            Rent.setText("Rent can be started on " + book_day.toString());
            return;
        }

        Rent.setText(String.format("Bike '%s' is ready for rent.\nCode: %07d", booking.get("bike_model"),(Long) booking.get("book_id")));
        Rent.setStyle("-fx-text-fill: green");
        ApplyButton.setText("Start rent");
        ApplyButton.setDisable(false);
//        booking_id = (Long) booking.get("book_id");
        return;
    }

    public void onSearchButtonClick(ActionEvent actionEvent) {
        searchBooking();
    }
}
