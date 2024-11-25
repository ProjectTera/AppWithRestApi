package com.example.appwithrestapi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TextView text1;
    public Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);
//        tryPost();
//        tryGet();
    }

    public void tryGet() {
        CallRestApi.callGET(getApplicationContext(), this);
    }

    public void tryPost() {
        Order newOrder = new Order("Jolli Hotdog", "999");
        String payload = gson.toJson(newOrder);
        text1.setText(payload);
        CallRestApi.callPOST(getApplicationContext(), this, payload);
    }

    public void processData(String response) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Order>>(){}.getType();
        List<Order> orderList = gson.fromJson(response, listType);
        Order firstSample = orderList.get(6);
        String text = "Your first order is " + firstSample.getQuantity() + " " + firstSample.getProductName();
        text1.setText(text);

    }

    public void finalizeData(String response) {
        text1.setText(response);

    }
}