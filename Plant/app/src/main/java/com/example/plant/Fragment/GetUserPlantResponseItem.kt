package com.example.plant.Fragment


import com.google.gson.annotations.SerializedName

data class GetUserPlantResponseItem(
    @SerializedName("plantCategory")
    val plantCategory: String? = null,
    @SerializedName("plantCreatetime")
    val plantCreatetime: Any? = null,
    @SerializedName("plantCultivateTip")
    val plantCultivateTip: Any? = null,
    @SerializedName("plantDistributionName")
    val plantDistributionName: Any? = null,
    @SerializedName("plantEnglishName")
    val plantEnglishName: Any? = null,
    @SerializedName("plantKoreanName")
    val plantKoreanName: String? = null,
    @SerializedName("plantModifytime")
    val plantModifytime: Any? = null,
    @SerializedName("plantNo")
    val plantNo: Int? = null,
    @SerializedName("plantOrigin")
    val plantOrigin: Any? = null,
    @SerializedName("plantScientificName")
    val plantScientificName: Any? = null,
    @SerializedName("userNo")
    val userNo: Int? = null,
    @SerializedName("userPlantActive")
    val userPlantActive: Int? = null,
    @SerializedName("userPlantCreatetime")
    val userPlantCreatetime: Any? = null,
    @SerializedName("userPlantModifytime")
    val userPlantModifytime: String? = null,
    @SerializedName("userPlantName")
    val userPlantName: String? = null,
    @SerializedName("userPlantNo")
    val userPlantNo: Int? = null,
    @SerializedName("warehousePlantNo")
    val warehousePlantNo: Int? = null
)