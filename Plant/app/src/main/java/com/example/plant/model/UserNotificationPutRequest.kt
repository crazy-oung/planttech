package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class UserNotificationPutRequest(
    @SerializedName("userMileageNo")
    val userMileageNo: Int,
    @SerializedName("userNo")
    val userNo: Int,
    @SerializedName("userNotificationActive")
    val userNotificationActive: Int,
    @SerializedName("userNotificationContent")
    val userNotificationContent: String,
    @SerializedName("userNotificationNo")
    val userNotificationNo: Int,
    @SerializedName("userNotificationType")
    val userNotificationType: Int,
    @SerializedName("userPlantNo")
    val userPlantNo: Int
)