package com.rlite.amitbhadana.restapi

import com.test.myapp.modal.UserInfo
import com.test.myapp.modal.VideoInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("/abyt/getData.php")
    fun getVideo(@Field("access_token") access_token: String): Call<VideoInfo>

    @POST("/abyt/logindemo.php")
    fun login(@Body userInfo: UserInfo): Call<ResponseBody>

}