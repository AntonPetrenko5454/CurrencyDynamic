module com.example.currencydynamic {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.currencydynamic to javafx.fxml;
    exports com.example.currencydynamic;
}