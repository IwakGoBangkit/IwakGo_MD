package com.bangkit.fishery_app.ui.screen.scan_result

data class ScanResultState(
    val isLoading: Boolean = false,
    val condition: String = "",
    val errorMessage: String? = null
)