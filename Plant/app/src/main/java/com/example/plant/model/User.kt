package com.example.plant.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey
    val userName: String,
    val userBirthdate: String,
    val userCreatetime: String,
    val userEmail: String,
    val userId: String,
    val userMileage: Int,
    val userModifytime: String,
    val userNickname: String,
    val userNo: Int,
    val userType: String

)
