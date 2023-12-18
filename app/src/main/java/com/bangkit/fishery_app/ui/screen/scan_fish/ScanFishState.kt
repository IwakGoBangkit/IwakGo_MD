package com.bangkit.fishery_app.ui.screen.scan_fish

data class ScanFishState(
    val isLoading: Boolean = false,
    val condition: String? = "",
    val errorMessage: String? = null
)