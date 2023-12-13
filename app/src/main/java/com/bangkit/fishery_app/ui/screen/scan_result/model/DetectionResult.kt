package com.bangkit.fishery_app.ui.screen.scan_result.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class DetectionResult(
    val condition: String,
): Parcelable