package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class BidListGetResponseItem(
    @SerializedName("productBidDate")
    val productBidDate: String,
    @SerializedName("productPrice")
    val productPrice: Int
)