package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class LoginResponseFail(
    @SerializedName("error")
    val error: Error
)