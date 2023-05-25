package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class PlantListRequest(
    @SerializedName("beginPage")
    val beginPage: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("plantWarehouseNo")
    val plantWarehouseNo: Int,
    @SerializedName("searchKeyword")
    val searchKeyword: String,
    @SerializedName("tab")
    val tab: String
)