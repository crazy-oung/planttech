package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class UserMilageResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)