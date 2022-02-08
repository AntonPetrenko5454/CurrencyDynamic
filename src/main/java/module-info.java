module com.example.currencydynamic {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.currencydynamic to javafx.fxml;
    exports com.example.currencydynamic;
}