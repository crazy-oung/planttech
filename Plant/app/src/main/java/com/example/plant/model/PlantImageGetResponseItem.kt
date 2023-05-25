package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class PlantImageGetResponseItem(
    @SerializedName("plantColorCreatetime")
    val plantColorCreatetime: String,
    @SerializedName("plantColorGrade")
    val plantColorGrade: String,
    @SerializedName("plantColorNo")
    val plantColorNo: Int,
    @SerializedName("plantColorPic")
    val plantColorPic: String,
    @SerializedName("plantWarehouseNo")
    val plantWarehouseNo: Int
)