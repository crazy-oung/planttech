package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class PlantListResponseItem(
    @SerializedName("plantCategory")
    val plantCategory: String,
    @SerializedName("plantCreatetime")
    val plantCreatetime: String,
    @SerializedName("plantCultivateTip")
    val plantCultivateTip: String,
    @SerializedName("plantDistributionName")
    val plantDistributionName: Any,
    @SerializedName("plantEnglishName")
    val plantEnglishName: Any,
    @SerializedName("plantKoreanName")
    val plantKoreanName: String,
    @SerializedName("plantModifytime")
    val plantModifytime: String,
    @SerializedName("plantNo")
    val plantNo: Int,
    @SerializedName("plantOrigin")
    val plantOrigin: String,
    @SerializedName("plantScientificName")
    val plantScientificName: String
)