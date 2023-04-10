package com.example.plant.api

import com.example.plant.model.LedResponse
import com.example.plant.model.PlantInfo
import com.example.plant.model.SensorControlTf
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @GET("test")
    fun getPlant(): Call<List<PlantInfo>>

    @POST("SensorControl/LED")
    fun LEDRequest(@Query("userNo") userNo: Int, @Query("ledTf") ledTf: Int ): Call<LedResponse>

}
