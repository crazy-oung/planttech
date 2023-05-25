package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class UserMeResponse(
    @SerializedName("userBirthdate")
    val userBirthdate: String,
    @SerializedName("userCreatetime")
    val userCreatetime: String,
    @SerializedName("userEmail")
    val userEmail: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userMileage")
    val userMileage: Int,
    @SerializedName("userModifytime")
    val userModifytime: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("userNickname")
    val userNickname: String,
    @SerializedName("userNo")
    val userNo: Int,
    @SerializedName("userPw")
    val userPw: String,
    @SerializedName("userType")
    val userType: String
)