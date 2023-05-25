package com.example.plant.api

import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.internal.authenticator.JavaNetAuthenticator
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager

object ApiClient {
    private val retrofitService: RetrofitService
    val retrofit: Retrofit
    val baseUrl: String = "http://dayounghan.com/" // http://192.168.0.21:8080/
    var clientBuilder = OkHttpClient.Builder()            // 여기랑
    var loggingInterceptor = HttpLoggingInterceptor()    // 여기,



    init {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(loggingInterceptor)



        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.cookieJar(JavaNetCookieJar(CookieManager())).build())                // 여기를 추가해주면 된다.
            .build()
        retrofitService = retrofit.create(RetrofitService::class.java)
    }

    fun getApiInterface(): RetrofitService = retrofitService
}