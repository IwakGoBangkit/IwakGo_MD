package com.bangkit.fishery_app.ui.screen.add_post

import androidx.lifecycle.ViewModel
//import com.bangkit.fishery_app.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
//    private val postRepository: PostRepository
): ViewModel() {

    private val _state = MutableStateFlow(AddPostState())
    val state = _state.asStateFlow()

    fun onEvent(event: AddPostEvent) {
        when(event) {
            is AddPostEvent.OnDescriptionChanged -> {
                _state.update {
                    it.copy(
                        description = event.description
                    )
                }
            }
            is AddPostEvent.OnLocationChanged -> {
                _state.update {
                    it.copy(
                        location = event.location
                    )
                }
            }
            is AddPostEvent.OnPhoneNumberChanged -> {
                _state.update {
                    it.copy(
                        phoneNumber = event.phoneNumber
                    )
                }
            }
            is AddPostEvent.OnPriceChanged -> {
                _state.update {
                    it.copy(
                        price = event.price
                    )
                }
            }
            is AddPostEvent.OnTitleChanged -> {
                _state.update {
                    it.copy(
                        title = event.title
                    )
                }
            }
            AddPostEvent.ResetState -> {
                _state.update {
                    AddPostState()
                }
            }
            is AddPostEvent.SetLoadingState -> {
                _state.update {
                    it.copy(
                        isLoading = event.isLoading
                    )
                }
            }
        }
    }
}