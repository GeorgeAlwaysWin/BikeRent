package bikerentUI;

import bikerentUI.client.ClientChangeAddressController;
import bikerentUI.client.ClientChangeController;
import bikerentUI.client.ClientController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.HashMap;

public class ScreenController {
//    private final HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene scene;
//    private final PropertyChangeSupport reporter;
    public enum Page {Hello, Client, Admin, Worker, ClientChange, ClientChangePassport, ClientChangePassword, ClientChangeAddress, ClientBooking, Bikes};
    private Page currentScene;
//    public FXMLLoader fxmlLoader;
    public final HashMap <Page, String> fxmls = new HashMap<Page, String>();

    private ScreenController(){
//        this.reporter = new PropertyChangeSupport(this);
//        this.addPropertyChangeListener(new HelloController());
//        this.addPropertyChangeListener(new ClientController());
//        this.addPropertyChangeListener(new ClientChangeController());
//        this.addPropertyChangeListener(new ClientChangeAddressController());

        this.fxmls.put(Page.Hello, "hello-view.fxml");
        this.fxmls.put(Page.Client, "client/client-view.fxml");
        this.fxmls.put(Page.Admin, "admin/admin-view.fxml");
        this.fxmls.put(Page.Worker, "worker/worker-view.fxml");
        this.fxmls.put(Page.ClientChange, "client/client-change-view.fxml");
        this.fxmls.put(Page.ClientBooking, "client/client-booking.fxml");
        this.fxmls.put(Page.ClientChangeAddress, "client/client-change-address-view.fxml");
        this.fxmls.put(Page.ClientChangePassport, "client/client-change-passport-view.fxml");
        this.fxmls.put(Page.ClientChangePassword, "client/client-change-password-view.fxml");
        this.fxmls.put(Page.Bikes, "bikes-view.fxml");

    }

//    public void addPropertyChangeListener(PropertyChangeListener pcl) {
//        reporter.addPropertyChangeListener(pcl);
//    }
//
//    public void removePropertyChangeListener(PropertyChangeListener pcl) {
//        reporter.removePropertyChangeListener(pcl);
//    }

//    public void notify(String name, String old_val, String new_val){
//        reporter.firePropertyChange(name,old_val,new_val);
//    }

    private static class ScreenControllerHolder {
        public static final ScreenController HOLDER_INSTANCE = new ScreenController();
    }

    public static ScreenController getInstance(){
        return ScreenController.ScreenControllerHolder.HOLDER_INSTANCE;
    }

    public void setScene(Scene sc) {
//        reporter.firePropertyChange("scene", this.scene, sc);
        this.scene = sc;
    }

    public Scene getScene() {
        return (this.scene);
    }

//    public void addScreen(String name, Pane pane){
//        screenMap.put(name, pane);
//    }

//    protected void removeScreen(String name){
//        screenMap.remove(name);
//    }

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