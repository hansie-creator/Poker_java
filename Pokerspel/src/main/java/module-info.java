module be.inf1.pokerspel {
    requires javafx.controls;
    requires javafx.fxml;

    opens be.inf1.pokerspel to javafx.fxml;
    exports be.inf1.pokerspel;
}
