module com.appfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.appfx to javafx.fxml;
    exports com.appfx;
}
