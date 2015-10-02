package com.brandonjf.weathervain;

/**
 * Created by brandon on 10/1/15.
 */
public interface VolleyResponseInterface {

    void onWeatherSuccess(String degrees);

    void onPhotoSuccess(String imageUrl);
}
