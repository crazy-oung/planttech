package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class UserNotificationDeleteResponse(
    @SerializedName("data")
    val `data`: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)