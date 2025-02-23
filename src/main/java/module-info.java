module bikerent {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

//    opens bikerentmodel to javafx.fxml;
    exports bikerentmodel;
    exports bikerentUI;
    opens bikerentUI to javafx.fxml;
    exports bikerentUI.client;
    opens bikerentUI.client to javafx.fxml;
}
