package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class BoardProductResponseItem(
    @SerializedName("isBookmark")
    val isBookmark: Int,
    @SerializedName("plantNo")
    val plantNo: Int,
    @SerializedName("productCount")
    val productCount: Int,
    @SerializedName("productName")
    val productName: String,
    @SerializedName("productPrice")
    val productPrice: Int
)