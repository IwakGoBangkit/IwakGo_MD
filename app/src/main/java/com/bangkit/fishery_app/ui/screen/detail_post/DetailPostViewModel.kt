package com.bangkit.fishery_app.ui.screen.detail_post

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
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _state = MutableStateFlow(DetailPostState())
    val state = _state.asStateFlow()

    fun getDetailPostById(id: Int) = viewModelScope.launch {
        postRepository.getDetailPost(id).collect { result ->
            when (result) {
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
                            detailPost = result.data
                        )
                    }
                }
            }
        }
    }

    fun getCommentById(id: Int) = viewModelScope.launch {
        postRepository.getComment(id).collect { result ->
            when (result) {
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
                            listComment = result.data
                        )
                    }
                }
            }
        }
    }

    private fun addComment(idPost: Int, username: String, photoProfile: String, comment: String) =
        viewModelScope.launch {
            val user = firebaseAuth.getLoggedUser()
            postRepository.addComment(
                idPost = idPost,
                username = user?.username ?: username,
                photoProfile = user?.photoUrl ?: photoProfile,
                comment = comment
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.message,
                                isCommentSuccessful = false
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
                                postCommentResult = result.data,
                                isCommentSuccessful = true
                            )
                        }
                    }
                }
            }
        }

    fun onEvent(event: DetailPostEvent) {
        when (event) {
            is DetailPostEvent.AddComment -> {
                addComment(
                    idPost = event.idPost,
                    username = event.username,
                    photoProfile = event.profilePhoto,
                    comment = event.comment
                )
            }
            is DetailPostEvent.OnCommentChanged -> {
                _state.update {
                    it.copy(
                        inputComment = event.comment
                    )
                }
            }

            is DetailPostEvent.SetLoadingState -> {
                _state.update {
                    it.copy(
                        isLoading = event.isLoading
                    )
                }
            }
        }
    }
}