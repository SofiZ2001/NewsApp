package com.example.newsapp

import com.example.domain.model.UserModel
import com.example.domain.usecase.GetUsersUseCase
import com.example.newsapp.viewmodel.UserViewModel
import com.example.newsapp.viewmodel.UserViewModel.UserState
import com.example.newsapp.viewmodel.UserViewModel.UserState.Error
import com.example.newsapp.viewmodel.UserViewModel.UserState.Idle
import com.example.newsapp.viewmodel.UserViewModel.UserState.Success
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @MockK
    lateinit var useCase: GetUsersUseCase

    @RelaxedMockK
    lateinit var usersList: List<UserModel>

    private lateinit var viewModel: UserViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)

        viewModel = UserViewModel(useCase)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel get users, return success`() = runTest(testDispatcher) {
        lateinit var lastState: UserState
        coEvery { useCase.invoke() } returns Result.success(usersList)

        viewModel.getUsers()

        val job = launch {
            viewModel.userState.collect { state ->
                lastState = state
            }
        }
        advanceUntilIdle()

        assertThat(lastState).isEqualTo(Success(usersList))
        job.cancel()
    }

    @Test
    fun `when viewmodel get users then fails`() = runTest(testDispatcher) {
        lateinit var lastState: UserState
        coEvery { useCase.invoke() } returns Result.failure(Throwable())

        viewModel.getUsers()

        val job = launch {
            viewModel.userState.collect { state ->
                lastState = state
            }
        }
        advanceUntilIdle()

        assertThat(lastState).isEqualTo(Error)
        job.cancel()
    }

    @Test
    fun `when viewmodel is initialized the first state is idle`() {
        val initialState = viewModel.userState.value

        assertThat(initialState).isEqualTo(Idle)
    }
}