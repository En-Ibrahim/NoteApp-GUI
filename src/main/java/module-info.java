module org.main.note_gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.main to javafx.fxml;
    exports org.main;
}