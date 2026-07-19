package com.frostre1997.tweetfrost.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourpackage.tweetfrost.data.models.Tweet
import com.yourpackage.tweetfrost.data.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val tweets: List<Tweet> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class HomeViewModel(
    private val apiService: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun loadTimeline() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                val response = apiService.getHomeTimeline()
                if (response.isSuccessful) {
                    response.body()?.let { tweets ->
                        _uiState.value = _uiState.value.copy(tweets = tweets, isLoading = false)
                    } ?: run {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = "Empty response"
                        )
                    }
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Error: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Network error: ${e.message}"
                )
            }
        }
    }

    fun postNewTweet(text: String) {
        viewModelScope.launch {
            try {
                val response = apiService.postTweet(text)
                if (response.isSuccessful) {
                    // Reload timeline after posting
                    loadTimeline()
                } else {
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "Failed to post: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Post failed: ${e.message}"
                )
            }
        }
    }
}
