package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class GetProductGradesListResponseItem(
    @SerializedName("plantNo")
    val plantNo: Int,
    @SerializedName("plantScoreName")
    val plantScoreName: String,
    @SerializedName("plantScoreNo")
    val plantScoreNo: Int,
    @SerializedName("productName")
    val productName: String,
    @SerializedName("productNo")
    val productNo: Int,
    @SerializedName("productPrice")
    val productPrice: Int,
    @SerializedName("warehousePlantNo")
    val warehousePlantNo: Int
)