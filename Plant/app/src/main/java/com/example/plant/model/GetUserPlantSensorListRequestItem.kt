package com.example.plant.model

data class GetUserPlantSensorListRequestItem(
    val dhtNo: Int? = null,
    val humi: Double? = null,
    val light: Double? = null,
    val photoRegistorNo: Int? = null,
    val plantNo: Int? = null,
    val plantSensorCreatetime: String? = null,
    val plantSensorNo: Int? = null,
    val temp: Double? = null,
    val warehousePlantNo: Int? = null,
    val waterTemp: Double? = null,
    val waterTempNo: Int? = null
)