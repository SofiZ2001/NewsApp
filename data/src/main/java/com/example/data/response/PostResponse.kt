package com.example.data.response

import com.example.domain.model.PostModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class PostResponse(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String
)

fun PostResponse.toModel(): PostModel = PostModel(title, content)

fun List<PostResponse>.toListModel(): List<PostModel> = this.map { it.toModel() }
