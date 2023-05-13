package com.example.plant.model

import java.util.Date

data class Plant (
    val plantName  : String,
    val plantScore : Int,
    val startDate : String,
    val plantVariety : String,
    val plantState : String,
    var plantStar : Boolean)
    //val temperature  : Int)