package com.example.manne.fiokaapp.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.manne.fiokaapp.BuildConfig;
import com.example.manne.fiokaapp.CheckConnection;
import com.example.manne.fiokaapp.LoggingInterceptor;
import com.example.manne.fiokaapp.model.PhotoModel;
import com.example.manne.fiokaapp.model2.UserPhoto;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manne on 16.1.2018.
 */

public class RestApi {
    public static final int request_max_time_tn_seconds=20;

    public Context activity;

    public RestApi(Context activity){
        this.activity=activity;
    }

    public Retrofit getRetrofitInstance(){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .readTimeout(request_max_time_tn_seconds, TimeUnit.SECONDS)
                .connectTimeout(request_max_time_tn_seconds, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
//                .baseUrl(ApiConstants.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    public ApiService request(){
        return getRetrofitInstance().create(ApiService.class);
    }

    public Call<PhotoModel> getPhotos(String feature){
        return request().getPhotos(feature);
    }

    public void checkInternet(Runnable callback) {
        if (CheckConnection.CheckInternetConnectivity(activity, true, callback))
            callback.run();
    }

    public void checkInternet(Runnable callback, boolean showConnectionDialog) {
        if (CheckConnection.CheckInternetConnectivity(activity, showConnectionDialog, callback))
            callback.run();
        else {
            Toast.makeText(activity, "Connection failed, please check your connection settings", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkInternet(Runnable callback, boolean showConnectionDialog, String message) {
        if (CheckConnection.CheckInternetConnectivity(activity, showConnectionDialog, callback))
            callback.run();
        else {
            if (showConnectionDialog)
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
            else
                Log.d("Connection failed", "" + message);
        }
    }

    public Call<PhotoModel> getPhotosSearch(String keyword)
    {
        return request().getPhotosSearch(keyword);
    }


}
