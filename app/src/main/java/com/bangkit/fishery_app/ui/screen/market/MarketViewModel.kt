package com.bangkit.fishery_app.ui.screen.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.fishery_app.data.repository.PostRepository
import com.bangkit.fishery_app.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MarketState())
    val state = _state.asStateFlow()

    init {
        getListPosts()
    }

    private fun getListPosts() = viewModelScope.launch {
        postRepository.getAllPost().collect { result ->
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
                            listPosts = result.data
                        )
                    }
                }
            }
        }
    }

    private fun searchPost(query: String) = viewModelScope.launch {
        postRepository.searchPost(query).collect { result ->
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
                            listPosts = result.data
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: MarketEvent) {
        when (event) {
            is MarketEvent.OnSearchQueryChanged -> {
                _state.update {
                    it.copy(
                        title = event.query
                    )
                }
            }
            is MarketEvent.GetPosts -> {
                searchPost(state.value.title)
            }
        }
    }
}