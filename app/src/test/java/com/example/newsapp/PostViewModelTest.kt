package com.example.newsapp

import com.example.domain.model.PostModel
import com.example.domain.usecase.GetPostUseCase
import com.example.newsapp.viewmodel.PostViewModel
import com.example.newsapp.viewmodel.PostViewModel.PostState
import com.example.newsapp.viewmodel.PostViewModel.PostState.Error
import com.example.newsapp.viewmodel.PostViewModel.PostState.Idle
import com.example.newsapp.viewmodel.PostViewModel.PostState.Success
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
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
class PostViewModelTest {

    @MockK
    lateinit var useCase: GetPostUseCase

    private val postList = listOf(
        PostModel(FIRST_TITLE, CONTENT),
        PostModel(SECOND_TITLE, CONTENT)
    )

    private lateinit var viewModel: PostViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)

        viewModel = PostViewModel(useCase)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel get posts, return success`() = runTest(testDispatcher) {
        lateinit var lastState: PostState
        coEvery { useCase.invoke() } returns Result.success(postList)

        viewModel.getPosts()

        val job = launch {
            viewModel.postState.collect { state ->
                lastState = state
            }
        }
        advanceUntilIdle()

        assertThat(lastState).isEqualTo(Success(postList))
        job.cancel()
    }

    @Test
    fun `when viewmodel get posts then fails`() = runTest(testDispatcher) {
        lateinit var lastState: PostState
        coEvery { useCase.invoke() } returns Result.failure(Throwable())

        viewModel.getPosts()

        val job = launch {
            viewModel.postState.collect { state ->
                lastState = state
            }
        }
        advanceUntilIdle()

        assertThat(lastState).isEqualTo(Error(ERROR_MESSAGE))
        job.cancel()
    }

    @Test
    fun `when viewmodel is initialized the first state is idle`() {
        val initialState = viewModel.postState.value

        assertThat(initialState).isEqualTo(Idle)
    }

    @Test
    fun `when filterPost is called with blank and returns the original the postList`() = runTest {
        val inputText = ""
        coEvery { useCase.invoke() } returns Result.success(postList)

        viewModel.getPosts()
        advanceUntilIdle()
        viewModel.filterPost(inputText)

        assertThat(viewModel.postState.value).isEqualTo(Success(postList))

    }

    @Test
    fun `when filterPost is called with coincidences and returns filtered list`() = runTest {
        val inputText = FIRST
        coEvery { useCase.invoke() } returns Result.success(postList)

        viewModel.getPosts()
        advanceUntilIdle()
        viewModel.filterPost(inputText)

        val filteredData = (viewModel.postState.value as Success).posts
        assertThat(filteredData.size).isEqualTo(ONE)
    }

    @Test
    fun `when filterPost is called with no coincidences and returns empty list`() = runTest {
        val inputText = THIRD
        coEvery { useCase.invoke() } returns Result.success(postList)

        viewModel.getPosts()
        advanceUntilIdle()
        viewModel.filterPost(inputText)

        val filteredData = (viewModel.postState.value as Success).posts
        assertThat(filteredData.size).isEqualTo(ZERO)
    }

    companion object {
        private const val FIRST_TITLE = "FIRST_TITLE"
        private const val CONTENT = "CONTENT"
        private const val SECOND_TITLE = "SECOND_TITLE"
        private const val FIRST = "FIRST"
        private const val THIRD = "THIRD"
        private const val ERROR_MESSAGE = "Hubo un problema"
        private const val ONE = 1
        private const val ZERO = 0

    }

}