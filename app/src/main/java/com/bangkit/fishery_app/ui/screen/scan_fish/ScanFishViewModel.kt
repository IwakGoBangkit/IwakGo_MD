package com.bangkit.fishery_app.ui.screen.scan_fish

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
class ScanFishViewModel @Inject constructor(
    private val fishRepository: FishRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ScanFishState())
    val state = _state.asStateFlow()

    fun uploadImage(image: File) = viewModelScope.launch {
        fishRepository.scanFish(image).collect{result ->
            when(result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            condition = result.data.prediction
                        )
                    }
                }
            }
        }
    }

}