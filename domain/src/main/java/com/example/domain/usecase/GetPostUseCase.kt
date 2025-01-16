package com.example.domain.usecase;

import com.example.domain.model.PostModel;

interface GetPostUseCase {
    suspend operator fun invoke(): Result<List<PostModel>>
}
