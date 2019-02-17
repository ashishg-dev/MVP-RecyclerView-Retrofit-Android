package com.test.myapp.restapi

import com.test.myapp.Constant.APP_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {

        private val interceptor = HttpLoggingInterceptor()
        private val logging = interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)


        private val httpClient = OkHttpClient.Builder()
                .connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()

        private val builder = Retrofit.Builder()
                .baseUrl(APP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        fun <S> createService(serviceClass: Class<S>): S {
            val retrofit = builder.client(httpClient).build()
            return retrofit.create(serviceClass)
        }
    }
}
