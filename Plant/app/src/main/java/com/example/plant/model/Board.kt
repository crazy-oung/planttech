package com.example.plant.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Board (
    @SerializedName("articleNo")
    val articleNo : Int? = null,
    @SerializedName("userNo")
    val userNo : Int? = null,
    @SerializedName("plantNo")
    val plantNo : Int? = null,
    @SerializedName("articleTitle")
    val articleTitle : String? = null,
    @SerializedName("articleSubject")
    val articleSubject : String? = null,
    @SerializedName("articleContent")
    val articleContent : String? = null,
    @SerializedName("articleProductPrice")
    val articleProductPrice : String? = null,
    @SerializedName("articleCreatetime")
    val articleCreatetime :	String? = null,
    @SerializedName("articleModifytime")
    val articleModifytime :	String? = null,
    @SerializedName("plantCategory")
    val plantCategory :	String? = null,
    @SerializedName("plantAmount")
    val plantAmount : Int? = null
)