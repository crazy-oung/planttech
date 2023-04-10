package com.example.plant.model

import com.google.gson.annotations.SerializedName

data class PlantInfo (
    @SerializedName("plantNo") val plantNo:Int,
    @SerializedName("userNo") val userNo:Int,
    @SerializedName("plantName") val plantName:String,
    @SerializedName("plantNickname") val plantNickname:String,

){
    override fun toString(): String {
        return "plantNo : $plantNo\n" +
                "userNo : $userNo\n" +
                "plantName : $plantName\n" +
                "plantNickname : $plantNickname\n"
    }
}