package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class UserNotificationPostRequest(
    @SerializedName("userMileageNo")
    val userMileageNo: Int? = null,
    @SerializedName("userNo")
    val userNo: Int,
    @SerializedName("userNotificationActive")
    val userNotificationActive: Int? = null,
    @SerializedName("userNotificationContent")
    val userNotificationContent: String,
    @SerializedName("userNotificationNo")
    val userNotificationNo: Int? = null,
    @SerializedName("userNotificationType")
    val userNotificationType: Int,
    @SerializedName("userPlantNo")
    val userPlantNo: Int? = null
)