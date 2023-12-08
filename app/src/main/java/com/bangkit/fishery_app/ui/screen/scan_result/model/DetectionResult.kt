package com.bangkit.fishery_app.ui.screen.scan_result.model

import android.graphics.RectF
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetectionResult(
    val boundingBox: RectF,
    val fishName: String,
    val condition: String,
    val treatment: String
): Parcelable