package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.UserModel
import com.example.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    private val _userState = MutableStateFlow<UserState>(UserState.Idle)
    val userState: StateFlow<UserState> = _userState

    fun getUsers() {
        viewModelScope.launch {
            val response = getUsersUseCase()
            _userState.value = response.fold(
                onSuccess = { users -> UserState.Success(users) },
                onFailure = {
                    UserState.Error
                }
            )
        }
    }

    sealed class UserState {
        data object Idle : UserState()
        data class Success(val users: List<UserModel>) : UserState()
        data object Error : UserState()
    }
}