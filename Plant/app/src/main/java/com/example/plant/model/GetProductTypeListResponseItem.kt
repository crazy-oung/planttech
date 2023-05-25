package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class GetProductTypeListResponseItem(
    @SerializedName("hasInstant")
    val hasInstant: Int,
    @SerializedName("plantScoreName")
    val plantScoreName: String,
    @SerializedName("plantScoreNo")
    val plantScoreNo: Int,
    @SerializedName("plantScoreVal")
    val plantScoreVal: Int,
    @SerializedName("productCount")
    val productCount: Int,
    @SerializedName("productNo")
    val productNo: Int,
    @SerializedName("productPrice")
    val productPrice: Int
)