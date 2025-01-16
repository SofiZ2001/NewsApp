package com.example.domain.usecase

import com.example.domain.NewsRepository
import com.example.domain.model.PostModel
import javax.inject.Inject

class GetPostsUseCaseImp @Inject constructor(private val repository: NewsRepository) :
    GetPostUseCase {

    override suspend operator fun invoke(): Result<List<PostModel>> {
        return repository.getPosts()
    }

}