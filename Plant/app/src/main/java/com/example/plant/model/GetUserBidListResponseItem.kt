package com.example.plant.model

data class GetUserBidListResponseItem(
    val plantNo: Int? = null,
    val productActive: Int? = null,
    val productBidUserId: String? = null,
    val productCreatetime: String? = null,
    val productInstant: Int? = null,
    val productMileage: Any? = null,
    val productModifytime: String? = null,
    val productName: String? = null,
    val productPrice: Int? = null,
    val productScoreName: String? = null,
    val productScoreNo: Int? = null,
    val productType: Int? = null,
    val productWeekday: Int? = null,
    val userNo: Int? = null,
    val userPlantName: Any? = null,
    val userPlantNo: Any? = null,
    val warehousePlantNo: Int? = null
)