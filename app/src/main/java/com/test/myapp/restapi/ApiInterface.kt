package com.test.myapp.restapi

import com.test.myapp.modal.VideoInfo
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("/abyt/getData.php")
    fun getVideo(@Field("access_token") access_token: String): Call<VideoInfo>

}