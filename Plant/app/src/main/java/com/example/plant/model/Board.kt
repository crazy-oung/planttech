package com.example.plant.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Board (
    @SerializedName("articleNo")
    val articleNo : Int,
    @SerializedName("userNo")
    val userNo : Int,
    @SerializedName("plantNo")
    val plantNo : Int,
    @SerializedName("articleTitle")
    val articleTitle : String,
    @SerializedName("articleSubject")
    val articleSubject : String,
    @SerializedName("articleContent")
    val articleContent : String,
    @SerializedName("articleProductPrice")
    val articleProductPrice : String,
    @SerializedName("articleCreatetime")
    val articleCreatetime :	String,
    @SerializedName("articleModifytime")
    val articleModifytime :	String)