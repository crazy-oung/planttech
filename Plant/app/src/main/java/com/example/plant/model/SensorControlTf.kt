package com.example.plant.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.Date

data class SensorControlTf(
    //@SerializedName("sensorControlNo") val sensorControlNo:Int,
    @SerializedName("userNo") val userNo:Int,
   // @SerializedName("waterPumpTf") val waterPumpTf:Int,
    @SerializedName("ledTf") val ledTf:Int
    //@SerializedName("sensorUpdateTimestamp") val sensorUpdateTimestamp: LocalDateTime,

){
    override fun toString(): String {
        return "userNo : $userNo\n" +
                //"waterPumpTf : $waterPumpTf\n" +
                "ledTf : $ledTf\n"
                //"sensorUpdateTimestamp : $sensorUpdateTimestamp\n"

    }
}