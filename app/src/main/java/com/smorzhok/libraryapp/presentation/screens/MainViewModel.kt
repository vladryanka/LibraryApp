package com.smorzhok.libraryapp.presentation.screens

import androidx.lifecycle.ViewModel
import com.smorzhok.libraryapp.domain.GetDetailUseCase
import com.smorzhok.libraryapp.domain.GetFavoritesUseCase
import com.smorzhok.libraryapp.domain.LikeBookUseCase
import com.smorzhok.libraryapp.domain.RetrySearchUseCase
import com.smorzhok.libraryapp.domain.UnlikeBookUseCase

class MainViewModel(
    private val getDetailUseCase: GetDetailUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val likeBookUseCase: LikeBookUseCase,
    private val retrySearchUseCase: RetrySearchUseCase,
    private val searchUseCase: RetrySearchUseCase,
    private val unlikeBookUseCase: UnlikeBookUseCase
) : ViewModel() {
    /*private val _uiState = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        reload()
    }

    fun reload() {
        getAllPosts()
    }

    fun createNew() {
        viewModelScope.launch {
            createPostUseCase(
                userId = 101,
                title = "New Title",
                body = "New Body"
            )
        }
    }

    fun onDeletePost(id: Int) {
        viewModelScope.launch {
            deletePostUseCase(id)
        }
    }

    fun onEditPost(post: PostModel) {
        viewModelScope.launch {
            updatePostUseCase(
                id = post.id,
                userId = post.userId,
                title = "Updated Title",
                body = "Updated Body",
            )
        }
    }

    private fun getAllPosts() {
        _uiState.update { state ->
            state.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            getAllPostsUseCase().handle(
                onSuccess = { postList ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            postList = postList,
                            errorMessage = null
                        )
                    }
                },
                onError = ::onError
            )
        }
    }

    private fun onError(message: String) {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                postList = emptyList(),
                errorMessage = message
            )
        }
    }*/
}