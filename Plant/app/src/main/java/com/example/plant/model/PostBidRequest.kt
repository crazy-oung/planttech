package com.example.plant.model

data class PostBidRequest(
    val plantNo: Int,
    val plantScoreNo: Int,
    val productActive: Int? = null,
    val productBidMileage: Int,
    val productBidUser: Int,
    val productCreatetime: String? = null,
    val productExpirationTime: String,
    val productInstant: Int,
    val productModifytime: String? = null,
    val productName: String,
    val productNo: Int? = null,
    val productPrice: Int,
    val productType: Int,
    val productWeekdayTf: Int? = null,
    val userNo: Int,
    val warehousePlantNo: Int
)