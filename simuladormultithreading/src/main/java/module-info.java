module ac3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ac3 to javafx.fxml;
    exports ac3;
}
