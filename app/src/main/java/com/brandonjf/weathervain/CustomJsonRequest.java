package com.brandonjf.weathervain;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by brandon on 9/30/15.
 */
public class CustomJsonRequest extends JsonObjectRequest {

    private Priority mPriority;

    public CustomJsonRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        super(method, url, jsonRequest, listener, errorListener);
    }

    public void  setPriority(Priority priority){
        mPriority = priority;
    }

    @Override
    public Priority getPriority() {
        return mPriority == null ? Priority.NORMAL : mPriority;
    }

}
