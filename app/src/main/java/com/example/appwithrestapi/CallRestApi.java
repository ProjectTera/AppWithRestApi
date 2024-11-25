package com.example.appwithrestapi;

import android.content.Context;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CallRestApi {

    public static <T> void callGET(Context context, T activity) {
        String apiEndpoint = "https://uwtd7bmt5b.execute-api.ap-southeast-2.amazonaws.com/prod/orders";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiEndpoint,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (activity instanceof MainActivity) {
                            MainActivity mainActivity = (MainActivity) activity;
                            mainActivity.processData(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Has Error 3", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(context).add(stringRequest);
    }

    public static <T> void callPOST(Context context, T activity, String payload) {
        String apiEndpoint = "https://uwtd7bmt5b.execute-api.ap-southeast-2.amazonaws.com/prod/orders";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, apiEndpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (activity instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) activity;
                    mainActivity.finalizeData(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Has Error 4", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            public byte[] getBody() {
                // This method is where we add the JSON payload to the request body
                return payload.getBytes();
            }

            @Override
            public String getBodyContentType() {
                // Set the content type to application/json
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() {
                // Optionally, add custom headers, e.g., for authorization
                Map<String, String> headers = new HashMap<>();
                //headers.put("Authorization", "Bearer YOUR_TOKEN_HERE");
                return headers;
            }
        };

        Volley.newRequestQueue(context).add(stringRequest);
    }
}