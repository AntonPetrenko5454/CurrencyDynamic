package com.example.currencydynamic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class MainController {
    @FXML
    private ComboBox firstSymbolComboBox;
    @FXML
    private ComboBox secondSymbolComboBox;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private LineChart lineChart;

    @FXML
    void buttonClick(ActionEvent event) throws IOException {
        lineChart.getData().clear();
        String baseSymbol=firstSymbolComboBox.getValue().toString();
        String symbol=secondSymbolComboBox.getValue().toString();
        LocalDate start=startDate.getValue();
        LocalDate end=endDate.getValue();
        XYChart.Series series1=new XYChart.Series();
        List<Double> values=CurrencyAPI.getRates(baseSymbol,symbol,start,end);

        for (int i=0;i<values.size();i++)
        {
            series1.getData().add(new XYChart.Data(i,values.get(i)));

        }
        lineChart.getData().add(series1);


    }

    @FXML
    public void initialize() throws IOException {
        firstSymbolComboBox.getItems().addAll(CurrencyAPI.getSymbols());
        secondSymbolComboBox.getItems().addAll(CurrencyAPI.getSymbols());
    }
}