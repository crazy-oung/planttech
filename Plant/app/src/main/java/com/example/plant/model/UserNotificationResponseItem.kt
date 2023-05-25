package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class UserNotificationResponseItem(
    @SerializedName("productName")
    val productName: Any,
    @SerializedName("productNo")
    val productNo: Any,
    @SerializedName("userMileageNo")
    val userMileageNo: Any,
    @SerializedName("userNo")
    val userNo: Int,
    @SerializedName("userNotificationActive")
    val userNotificationActive: Int,
    @SerializedName("userNotificationContent")
    val userNotificationContent: String,
    @SerializedName("userNotificationCreatetime")
    val userNotificationCreatetime: String,
    @SerializedName("userNotificationNo")
    val userNotificationNo: Int,
    @SerializedName("userNotificationReadtime")
    val userNotificationReadtime: String,
    @SerializedName("userNotificationType")
    val userNotificationType: Int,
    @SerializedName("userPlantName")
    val userPlantName: String,
    @SerializedName("userPlantNo")
    val userPlantNo: Int,
    @SerializedName("warehouseNo")
    val warehouseNo: Int
)