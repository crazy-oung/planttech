package com.example.plant.model


import com.google.gson.annotations.SerializedName

data class PlantCategoryResponseItem(
    @SerializedName("plantCategoryName")
    val plantCategoryName: String,
    @SerializedName("plantCategoryNo")
    val plantCategoryNo: Int
)