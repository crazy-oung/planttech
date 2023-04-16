package com.example.plant.model

data class RecyclerInViewModel(
    val plantName  : String,
    val plantState : String,
    val temp : Int,
    val humidity : Int,
    val type : Int)