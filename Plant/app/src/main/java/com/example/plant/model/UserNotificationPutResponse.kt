package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class UserNotificationPutResponse(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)