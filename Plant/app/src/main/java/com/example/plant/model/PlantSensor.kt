package com.example.plant.model

import com.google.gson.annotations.SerializedName

data class PlantSensor (
    @SerializedName("plantSensorNo") val plantSensorNo:Int,
    @SerializedName("plantNo") val plantNo:Int,
    @SerializedName("dhtNo") val dhtNo:Int,
    @SerializedName("photoRegistorNo") val photoRegistorNo:Int,
    @SerializedName("waterTempNo") val waterTempNo:Int,
    @SerializedName("humi") val humi:Float,
    @SerializedName("temp") val temp:Float,
    @SerializedName("waterTemp") val waterTemp:Float,
    @SerializedName("light") val light:Float,
    @SerializedName("plantSensorTimestamp") val plantSensorTimestamp:String
){
    override fun toString(): String {
        return "plantSensorNo : $plantSensorNo\n" +
                "plantNo : $plantNo\n" +
                "dhtNo : $dhtNo\n" +
                "photoRegistorNo : $photoRegistorNo\n" +
                "waterTempNo : $waterTempNo\n" +
                "humi : $humi\n" +
                "temp : $temp\n" +
                "waterTemp : $waterTemp\n" +
                "light : $light\n" +
                "plantSensorTimestamp : $plantSensorTimestamp\n"
    }
}
