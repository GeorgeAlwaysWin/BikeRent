package bikerentUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

public class ScreenController {
    private Scene scene;
    public enum Page {Hello, Client, Admin, Worker, ClientChange, ClientChangePassport, UserChangePassword, ClientChangeAddress, ClientBooking, WorkerChange, AdminUserChange, AdminShopChange};
    private Page currentScene;
    public final HashMap <Page, String> fxmls = new HashMap<Page, String>();

    private ScreenController(){
        this.fxmls.put(Page.Hello, "hello-view.fxml");
        this.fxmls.put(Page.Client, "client/client-view.fxml");
        this.fxmls.put(Page.Admin, "admin/admin-view.fxml");
        this.fxmls.put(Page.Worker, "worker/worker-view.fxml");
        this.fxmls.put(Page.ClientChange, "client/client-change-view.fxml");
        this.fxmls.put(Page.ClientBooking, "client/client-booking-view.fxml");
        this.fxmls.put(Page.ClientChangeAddress, "client/client-change-address-view.fxml");
        this.fxmls.put(Page.ClientChangePassport, "client/client-change-passport-view.fxml");
        this.fxmls.put(Page.UserChangePassword, "user-change-password-view.fxml");
        this.fxmls.put(Page.WorkerChange, "worker/worker-change-view.fxml");
        this.fxmls.put(Page.AdminUserChange, "admin/admin-user-change-view.fxml");
        this.fxmls.put(Page.AdminShopChange, "admin/admin-shop-change-view.fxml");

    }

    private static class ScreenControllerHolder {
        public static final ScreenController HOLDER_INSTANCE = new ScreenController();
    }

    public static ScreenController getInstance(){
        return ScreenController.ScreenControllerHolder.HOLDER_INSTANCE;
    }

    public void setScene(Scene sc) {
        this.scene = sc;
    }

    public Scene getScene() {
        return (this.scene);
    }

    public void activate(Page page) throws IOException {
        String location = this.fxmls.get(page);
        Pane pane = FXMLLoader.load(getClass().getResource(location));
        scene.setRoot( pane );
        this.currentScene = page;
    }

    public Page getCurrentScreen(){
         return this.currentScene;
    }
}