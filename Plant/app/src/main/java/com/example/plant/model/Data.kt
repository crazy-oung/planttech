package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("userMileageAble")
    val userMileageAble: String,
    @SerializedName("userMileageContent")
    val userMileageContent: String,
    @SerializedName("userMileageCreatetime")
    val userMileageCreatetime: String,
    @SerializedName("userMileageNo")
    val userMileageNo: Int,
    @SerializedName("userMileagePayment")
    val userMileagePayment: Any,
    @SerializedName("userMileageValue")
    val userMileageValue: Int,
    @SerializedName("userNo")
    val userNo: Int
)