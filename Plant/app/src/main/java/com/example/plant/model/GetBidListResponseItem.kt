package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class GetBidListResponseItem(
    @SerializedName("plantNo")
    val plantNo: Int,
    @SerializedName("plantScoreNo")
    val plantScoreNo: Int,
    @SerializedName("productActive")
    val productActive: Int,
    @SerializedName("productCreatetime")
    val productCreatetime: String,
    @SerializedName("productExpirationTime")
    val productExpirationTime: String,
    @SerializedName("productInstant")
    val productInstant: Int,
    @SerializedName("productModifytime")
    val productModifytime: String,
    @SerializedName("productName")
    val productName: String,
    @SerializedName("productNo")
    val productNo: Int,
    @SerializedName("productPrice")
    val productPrice: Int,
    @SerializedName("productType")
    val productType: Int,
    @SerializedName("productWeekdayTf")
    val productWeekdayTf: Int,
    @SerializedName("userNo")
    val userNo: Int,
    @SerializedName("productBidUser")
    val productBidUser: String,
    @SerializedName("warehousePlantNo")
    val warehousePlantNo: Int
)