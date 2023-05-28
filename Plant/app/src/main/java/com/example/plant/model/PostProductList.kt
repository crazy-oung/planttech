package com.example.plant.model

data class PostProductList(
    val plantNo: Int,
    val plantScoreNo: Int,
    val productActive: Int? = null,
    val productBidMileage: Int? = null,
    val productBidUser: Int? = null,
    val productCreatetime: String? = null,
    val productExpirationTime: String? = null,
    val productInstant: Int,
    val productModifytime: String? = null,
    val productName: String,
    val productNo: Int? = null,
    val productPrice: Int,
    val productType: Int,
    val productWeekdayTf: Int? = null,
    val userNo: Int,
    val warehousePlantNo: Int? = null
)