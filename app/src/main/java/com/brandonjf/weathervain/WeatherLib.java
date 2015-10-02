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
    final static String API_ENDPOINT = "http://marsweather.ingenology.com/v1/latest/";
    public static VolleyResponseInterface volleyResponseInterface;
    private static WeatherLib mInstance;
    private ApplicationController applicationController = ApplicationController.getInstance();
    private Response.Listener responseListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                String maxTemp;
                response = response.getJSONObject("report");
                maxTemp = response.getString("max_temp");
                Log.d("WeatherLib", maxTemp);
                volleyResponseInterface.onWeatherSuccess(maxTemp);
                int bird = 1;
            } catch (Exception e) {

            }
        }
    };
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

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
