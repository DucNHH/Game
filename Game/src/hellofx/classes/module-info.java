module hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens ducnhh to javafx.fxml;
    
    exports ducnhh;
}
