package com.example.manne.fiokaapp.api;

import com.example.manne.fiokaapp.model.PhotoData;
import com.example.manne.fiokaapp.model.PhotoModel;
import com.example.manne.fiokaapp.model2.BlogUser;
import com.example.manne.fiokaapp.model2.UserPhoto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by manne on 16.1.2018.
 */

public interface ApiService {
    @GET("photos?"+ApiConstants.consumer_key)
    Call<PhotoModel> getPhotos (@Query("fearure") String featureString);

    @GET(ApiConstants.PHOTOS_ENDPOINT+"search?"+ApiConstants.consumer_key)
    Call<PhotoModel> getPhotosSearch(@Query("term") String featureString);

//    @GET("photos?"+ApiConstants.consumer_key)
//    Call<UserPhoto> getPhotos (@Query("fearure") String featureString);

//    @GET(ApiConstants.PHOTOS_ENDPOINT+"search?"+ApiConstants.consumer_key)
//    Call<UserPhoto> getPhotosSearch(@Query("term") String featureString);


    @FormUrlEncoded
    @POST("photos")
    Call<PhotoData> uploadPhoto(@Field("name") String stringName, @Body BlogUser blogUser);
}
