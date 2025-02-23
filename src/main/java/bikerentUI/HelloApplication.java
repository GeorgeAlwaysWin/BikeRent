package bikerentUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static ScreenController SC;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        SC = ScreenController.getInstance();
        SC.setScene(new Scene(fxmlLoader.load(), 320, 340));
        stage.setTitle("BikeRentStudio");
        stage.setScene(SC.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}