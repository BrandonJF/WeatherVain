package com.brandonjf.weathervain;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by brandon on 10/1/15.
 */
public class WeatherLib {
    private final static String API_KEY = "23ed6c48a8904099ee2e6df28cbb5f42";
    final static String API_ENDPOINT = "http://api.openweathermap.org/data/2.5/weather?units=imperial&zip=11211,us&APPID=" + API_KEY;
    public static VolleyResponseInterface volleyResponseInterface;
    private static WeatherLib mInstance;
    private ApplicationController applicationController = ApplicationController.getInstance();
    private Response.Listener responseListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                String temp;
                response = response.getJSONObject("main");
                temp = response.getString("temp");
                Log.d("WeatherLib", temp);
                volleyResponseInterface.onWeatherSuccess(temp);
            } catch (Exception e) {
                Log.d("WeatherVain", e.toString());
            }
        }
    };
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("WeatherVain", error.toString());
        }
    };

    private WeatherLib(VolleyResponseInterface volleyResponseInterface) {
        WeatherLib.volleyResponseInterface = volleyResponseInterface;
    }

    public static synchronized WeatherLib getInstance(VolleyResponseInterface volleyResponseInterface) {
        if (mInstance == null) {
            mInstance = new WeatherLib(volleyResponseInterface);
        }
        return mInstance;
    }

    public void getWeatherData() {
        CustomJsonRequest weatherRequest = new CustomJsonRequest(Request.Method.GET, API_ENDPOINT, null, responseListener, errorListener);
        weatherRequest.setPriority(Request.Priority.LOW);
        applicationController.addToRequestQueue(weatherRequest);

    }
}
