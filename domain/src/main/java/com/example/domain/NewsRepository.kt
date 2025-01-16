package com.example.domain

import com.example.domain.model.PostModel
import com.example.domain.model.UserModel

interface NewsRepository {

    suspend fun getPosts(): Result<List<PostModel>>
    suspend fun getUsers(): Result<List<UserModel>>
}