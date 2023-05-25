package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class FindPasswordResponse(
    @SerializedName("data")
    val `data`: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)