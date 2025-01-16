package com.example.data.repository

import com.example.data.response.toListModel
import com.example.data.service.NewsApi
import com.example.domain.NewsRepository
import com.example.domain.model.PostModel
import com.example.domain.model.UserModel
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(private val newsApi: NewsApi) : NewsRepository {

    override suspend fun getPosts(): Result<List<PostModel>> {
        return try {
            val callResponse = newsApi.getPosts()
            Result.success(callResponse.toListModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUsers(): Result<List<UserModel>> {
        return try {
            val callResponse = newsApi.getUsers()
            Result.success(callResponse.toListModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}