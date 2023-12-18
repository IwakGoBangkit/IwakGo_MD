package com.bangkit.fishery_app.ui.screen.scan_fish

import com.bangkit.fishery_app.data.source.remote.response.ScanResponse

data class ScanFishState(
    val isLoading: Boolean = false,
    val condition: ScanResponse? = null,
    val errorMessage: String? = null
)