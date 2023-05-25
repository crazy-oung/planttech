package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class UserRegisterRequest(
    @SerializedName("userBirthdate")
    val userBirthdate: String,
    @SerializedName("userEmail")
    val userEmail: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("userNickname")
    val userNickname: String,
    @SerializedName("userNo")
    val userNo: Int,
    @SerializedName("userPw")
    val userPw: String
)