package com.example.plant.model

data class GetUserPlantSensorListRequestItem(
    val dhtNo: Int,
    val humi: Double,
    val light: Double,
    val photoRegistorNo: Int,
    val plantNo: Int,
    val plantSensorCreatetime: String,
    val plantSensorNo: Int,
    val temp: Double,
    val warehousePlantNo: Int,
    val waterTemp: Double,
    val waterTempNo: Int
)