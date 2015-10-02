package com.brandonjf.weathervain;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by brandon on 9/30/15.
 */
public class ApplicationController extends Application {
    public static final String TAG = ApplicationController.class.getName();
    private static ApplicationController mInstance;
    private RequestQueue mRequestQueue;

    public static synchronized ApplicationController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public RequestQueue getRequestQueue(){
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancel(){
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }

}
