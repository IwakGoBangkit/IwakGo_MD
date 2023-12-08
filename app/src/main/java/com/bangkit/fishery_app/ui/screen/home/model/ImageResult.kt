package com.bangkit.fishery_app.ui.screen.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class ImageResult(
    val imageFile: File
) : Parcelable