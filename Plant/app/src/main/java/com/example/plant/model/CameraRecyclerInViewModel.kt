package com.example.plant.model

import android.graphics.Bitmap
import android.media.Image
import android.widget.ImageView

data class CameraRecyclerInViewModel(
    val cameraView : Bitmap,
    val plantName  : String,
    val plantState : String,
    val temp : Int,
    val humidity : Int,
    val type : Int
)
