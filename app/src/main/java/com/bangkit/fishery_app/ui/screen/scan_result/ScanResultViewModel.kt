package com.bangkit.fishery_app.ui.screen.scan_result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.fishery_app.data.repository.FishRepository
import com.bangkit.fishery_app.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class ScanResultViewModel @Inject constructor(
    private val fishRepository: FishRepository
): ViewModel() {

    private val _state = MutableStateFlow(ScanResultState())
    val state = _state.asStateFlow()


    fun onEvent(event: ScanResultEvent) {
        when(event) {
            ScanResultEvent.ResetState -> {
                _state.update {
                    ScanResultState()
                }
            }
            is ScanResultEvent.SetLoadingState -> {
                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }
            }
            is ScanResultEvent.GetConditionSuccess -> {
                _state.update {
                    it.copy(
                        isLoading = false,
                        condition = event.condition
                    )
                }
            }
            is ScanResultEvent.GetConditionFailed -> {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = event.errorMessage
                    )
                }
            }
        }
    }



}