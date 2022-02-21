package com.example.currencydynamic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CurrencyAPI {
    public CurrencyAPI()
    {

    }
    public static List<String> getSymbols() throws IOException {
        HttpURLConnection connection  = (HttpURLConnection) (new URL("https://www.currency-api.com/symbols")).openConnection();
        connection.setRequestMethod("GET");
        List<String> symbols=new ArrayList<>();
        InputStream response= connection.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(response));
        JSONObject jo = new JSONObject(reader.readLine());
        JSONArray ja = jo.getJSONArray("symbols");
        for (int i=0;i< ja.length();i++)
        {
            symbols.add(ja.getString(i));
        }
        reader.close();
        return symbols;
    }
    public static double getRate(String baseSymbol,String symbol, LocalDate date) throws IOException {
        String url=String.format("https://www.currency-api.com/rates?base=%s&date=%s&symbols=%s",baseSymbol,date,symbol);
        HttpURLConnection connection  = (HttpURLConnection) (new URL(url)).openConnection();
        connection.setRequestMethod("GET");
        InputStream response= connection.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(response));
        JSONObject jo = new JSONObject(reader.readLine());
        JSONObject rates= jo.getJSONObject("rates");
        return rates.getDouble(symbol);
    }
    public static List<Double> getRates(String baseSymbol, String symbol, LocalDate startDate,LocalDate endDate) throws IOException {
        List<Double> results=new ArrayList<>();
        LocalDate currentDate= startDate;
        while (currentDate.compareTo(endDate)<0)
        {
            results.add(getRate(baseSymbol,symbol,currentDate));
            currentDate=currentDate.plusDays(1);

        }
        return results;
    }

}
