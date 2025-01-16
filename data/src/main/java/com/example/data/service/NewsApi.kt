package com.example.data.service

import com.example.data.response.PostResponse
import com.example.data.response.UserResponse
import retrofit2.http.GET

interface NewsApi {

    @GET("/posts")
    suspend fun getPosts(): List<PostResponse>

    @GET("/users")
    suspend fun getUsers(): List<UserResponse>
}