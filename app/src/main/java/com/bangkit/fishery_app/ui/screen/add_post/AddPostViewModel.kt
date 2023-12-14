package com.bangkit.fishery_app.ui.screen.add_post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.fishery_app.data.repository.PostRepository
import com.bangkit.fishery_app.data.source.firebase.FirebaseAuth
import com.bangkit.fishery_app.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _state = MutableStateFlow(AddPostState())
    val state = _state.asStateFlow()

    fun onEvent(event: AddPostEvent) {
        when (event) {
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

            is AddPostEvent.OnImageChanged -> {
                _state.update {
                    it.copy(
                        image = event.image
                    )
                }
            }

            is AddPostEvent.AddPost -> {
                addPost(
                    username = event.username,
                    profilePhoto = event.profilePhoto,
                    title = event.title,
                    location = event.location,
                    phoneNumber = event.phoneNumber,
                    price = event.price,
                    description = event.description,
                    image = event.image
                )
            }
        }
    }

    private fun addPost(
        username: String,
        profilePhoto: String,
        title: String,
        location: String,
        phoneNumber: String,
        price: String,
        description: String,
        image: File?
    ) = viewModelScope.launch {
        val user = firebaseAuth.getLoggedUser()
        postRepository.addPost(
            username = user?.username ?: username,
            userProfilePhoto = user?.photoUrl ?: profilePhoto,
            title = title,
            description = description,
            location = location,
            phoneNumber = phoneNumber,
            price = price,
            photo = image
        ).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            addPostError = result.message,
                            addPostSuccessful = false
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
                            addPostSuccessful = true,
                            postResult = result.data
                        )
                    }
                }
            }
        }
    }
}