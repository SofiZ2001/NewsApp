package com.example.domain.usecase;

import com.example.domain.model.PostModel;

public interface GetPostUseCase {
    suspend operator fun invoke(): Result<List<PostModel>>
}
