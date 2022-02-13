package com.example.currencydynamic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.Random;

public class MainController {
    @FXML
    private LineChart lineChart;

    @FXML
    void buttonClick(ActionEvent event)
    {
        Random random=new Random();
        XYChart.Series series1=new XYChart.Series();
        for (int i=0;i<10;i++)
        {
            series1.getData().add(new XYChart.Data(i,random.nextInt(100)));
        }
        lineChart.getData().add(series1);
    }

}