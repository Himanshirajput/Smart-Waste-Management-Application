package com.example.capston.Network;

import com.example.capston.DataModels.DustbinInfoResponse;
import com.example.capston.DataModels.LogOutInputModel;
import com.example.capston.DataModels.LogOutResponse;
import com.example.capston.DataModels.LoginInputModel;
import com.example.capston.DataModels.NotificationResponse;
import com.example.capston.DataModels.RegisterInputModel;
import com.example.capston.DataModels.RegisterResponseModel;
import com.example.capston.DataModels.User;
import com.example.capston.DataModels.UserListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("/getNotifications")
    Call<NotificationResponse> getNotifications(@Query("rfID") String rfID);

    @GET("/getUserList")
    Call<UserListResponse> getuserList(@Query("accessToken") String accessToken);

    @GET("/getDustbinInfo")
    Call<DustbinInfoResponse> getDustbinInfo(@Query("accessToken") String accessToken);

    @POST("/user/userLogin")
    Call<User> loginUser(@Body LoginInputModel loginData);

    @POST("/user/userLogout")
    Call<LogOutResponse> logoutUser(@Body LogOutInputModel accessToken);

    @POST("/user/userRegister")
    Call<RegisterResponseModel> registerUser(@Body RegisterInputModel model);


}
