package com.example.plant.Fragment


import com.google.gson.annotations.SerializedName

data class GetUserPlantResponseItem(
    @SerializedName("plantCategory")
    val plantCategory: String,
    @SerializedName("plantCreatetime")
    val plantCreatetime: Any,
    @SerializedName("plantCultivateTip")
    val plantCultivateTip: Any,
    @SerializedName("plantDistributionName")
    val plantDistributionName: Any,
    @SerializedName("plantEnglishName")
    val plantEnglishName: Any,
    @SerializedName("plantKoreanName")
    val plantKoreanName: String,
    @SerializedName("plantModifytime")
    val plantModifytime: Any,
    @SerializedName("plantNo")
    val plantNo: Int,
    @SerializedName("plantOrigin")
    val plantOrigin: Any,
    @SerializedName("plantScientificName")
    val plantScientificName: Any,
    @SerializedName("userNo")
    val userNo: Int,
    @SerializedName("userPlantActive")
    val userPlantActive: Int,
    @SerializedName("userPlantCreatetime")
    val userPlantCreatetime: Any,
    @SerializedName("userPlantModifytime")
    val userPlantModifytime: String,
    @SerializedName("userPlantName")
    val userPlantName: Any,
    @SerializedName("userPlantNo")
    val userPlantNo: Int,
    @SerializedName("warehousePlantNo")
    val warehousePlantNo: Int
)