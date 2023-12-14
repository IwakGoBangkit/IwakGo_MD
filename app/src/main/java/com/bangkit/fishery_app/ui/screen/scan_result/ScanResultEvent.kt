package com.bangkit.fishery_app.ui.screen.scan_result

sealed class ScanResultEvent {
    data class SetLoadingState(val isLoading: Boolean): ScanResultEvent()
    data class GetConditionSuccess(val condition: String): ScanResultEvent()
    data class GetConditionFailed(val errorMessage: String) : ScanResultEvent()
    object ResetState : ScanResultEvent()
}