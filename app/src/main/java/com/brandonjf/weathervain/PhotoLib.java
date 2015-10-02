package com.brandonjf.weathervain;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by brandon on 10/1/15.
 */
public class PhotoLib {

    final static String FLICKR_KEY = "89fc995bb86c641046b32ec5722b31a9";
    final static String PHOTO_ENDPOINT = "https://api.flickr.com/services/rest/?format=json&nojsoncallback=1&sort=random&method=flickr.photos.search&" + "tags=longboard,girl&tag_mode=all&api_key=";
    private static PhotoLib mInstance;
    private ApplicationController applicationController = ApplicationController.getInstance();
    private VolleyResponseInterface volleyResponseInterface = null;
    private Response.Listener responseListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONArray images = response.getJSONObject("photos").getJSONArray("photo");
                int index = new Random().nextInt(images.length());
                JSONObject imageItem = images.getJSONObject(index);
                String imageUrl = "http://farm" + imageItem.getString("farm") +
                        ".static.flickr.com/" + imageItem.getString("server") + "/" +
                        imageItem.getString("id") + "_" + imageItem.getString("secret") + "_" + "c.jpg";
                volleyResponseInterface.onPhotoSuccess(imageUrl);
            } catch (Exception e) {

            }
        }
    };
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };

    private PhotoLib(VolleyResponseInterface volleyResponseInterface) {
        this.volleyResponseInterface = volleyResponseInterface;
    }

    public static synchronized PhotoLib getInstance(VolleyResponseInterface volleyResponseInterface) {
        if (mInstance == null) {
            mInstance = new PhotoLib(volleyResponseInterface);
        }
        return mInstance;
    }

    public void getRandomImage() {
        CustomJsonRequest photoRequest = new CustomJsonRequest(Request.Method.GET, PHOTO_ENDPOINT + FLICKR_KEY, null, responseListener, errorListener);
        photoRequest.setPriority(Request.Priority.LOW);
        applicationController.addToRequestQueue(photoRequest);
    }

}
