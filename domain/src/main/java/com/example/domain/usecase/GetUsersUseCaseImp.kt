package com.example.domain.usecase

import com.example.domain.NewsRepository
import com.example.domain.model.UserModel
import javax.inject.Inject

class GetUsersUseCaseImp @Inject constructor(private val repository: NewsRepository) :
    GetUsersUseCase {

    override suspend operator fun invoke(): Result<List<UserModel>> {
        return repository.getUsers()
    }
}