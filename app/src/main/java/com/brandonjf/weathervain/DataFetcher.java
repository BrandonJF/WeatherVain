package com.brandonjf.weathervain;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by brandon on 9/30/15.
 */
public class DataFetcher extends Application {
    private RequestQueue mRequestQueue;
    private static DataFetcher mInstance;
    public static final String TAG = DataFetcher.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }
    public static synchronized DataFetcher getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public <T> void add (Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancel(){
        mRequestQueue.cancelAll(TAG);
    }

}
