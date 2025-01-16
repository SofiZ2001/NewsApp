package com.example.data

import com.example.data.repository.NewsRepositoryImp
import com.example.data.response.PostResponse
import com.example.data.response.UserResponse
import com.example.data.service.NewsApi
import com.example.domain.NewsRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class NewsRepositoryTest {

    @MockK
    private lateinit var newsApi: NewsApi

    @RelaxedMockK
    private lateinit var postsList: List<PostResponse>

    @RelaxedMockK
    private lateinit var usersList: List<UserResponse>

    private lateinit var newsRepository: NewsRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        newsRepository = NewsRepositoryImp(newsApi)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when repository get posts then returns success`() = runTest {
        coEvery { newsApi.getPosts() } returns postsList

        val result = newsRepository.getPosts()

        assertThat(result.isSuccess).isTrue()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when repository get users then returns success`() = runTest {
        coEvery { newsApi.getUsers() } returns usersList

        val result = newsRepository.getUsers()

        assertThat(result.isSuccess).isTrue()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when repository get posts then fails`() = runTest {
        coEvery { newsApi.getPosts() } throws RuntimeException(SERVICE_ERROR_MESSAGE)

        val result = newsRepository.getPosts()

        assertThat(result.isFailure).isTrue()
        assert(result.exceptionOrNull() is RuntimeException)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when repository get users then fails`() = runTest {
        coEvery { newsApi.getUsers() } throws RuntimeException(SERVICE_ERROR_MESSAGE)

        val result = newsRepository.getUsers()

        assertThat(result.isFailure).isTrue()
        assert(result.exceptionOrNull() is RuntimeException)
    }

    companion object {
        private const val SERVICE_ERROR_MESSAGE = "API error"
    }
}