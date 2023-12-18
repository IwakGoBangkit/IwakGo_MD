package com.bangkit.fishery_app.ui.screen.scan_fish

import com.bangkit.fishery_app.ui.screen.scan_result.model.DetectionResult

data class ScanFishState(
    val isLoading: Boolean = false,
    val condition: DetectionResult? = null,
    val errorMessage: String? = null
)