package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class UserRegisterResponse(
    @SerializedName("data")
    val data: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)