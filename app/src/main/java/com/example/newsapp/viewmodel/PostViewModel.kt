package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.PostModel
import com.example.domain.usecase.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostUseCase
) : ViewModel() {
    private val _postState = MutableStateFlow<PostState>(PostState.Idle)
    val postState: StateFlow<PostState> = _postState

    private var postList: List<PostModel> = emptyList()

    fun getPosts() = viewModelScope.launch {
        val response = getPostsUseCase()
        _postState.value = response.fold(
            onSuccess = { posts ->
                postList = posts
                PostState.Success(posts)
            },
            onFailure = {
                PostState.Error
            }
        )
    }

    fun filterPost(inputText: String) {
        val filteredPosts = if (inputText.isBlank()) postList
        else postList.filter { post -> post.title.contains(inputText, ignoreCase = true) }
        _postState.value = PostState.Success(filteredPosts)
    }

    sealed class PostState {
        data object Idle : PostState()
        data class Success(val posts: List<PostModel>) : PostState()
        data object Error : PostState()
    }
}