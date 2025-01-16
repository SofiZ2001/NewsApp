package com.example.domain.usecase

import com.example.domain.model.UserModel

interface GetUsersUseCase {
    suspend operator fun invoke(): Result<List<UserModel>>
}