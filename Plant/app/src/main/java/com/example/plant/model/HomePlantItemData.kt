package com.example.plant.model

data class HomePlantItemData(
    val plantNickname : String,
    val plantName : String,
    val plantCategory : String,
    val plantState : String,
    val plantTemp : Double,
    val plantHumi : Double,
    val plantNo : Int,
    val plantWarehouseNo : Int
)
