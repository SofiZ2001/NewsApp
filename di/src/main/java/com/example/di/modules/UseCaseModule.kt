package com.example.di.modules

import com.example.domain.usecase.GetPostUseCase
import com.example.domain.usecase.GetPostsUseCaseImp
import com.example.domain.usecase.GetUsersUseCase
import com.example.domain.usecase.GetUsersUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideGetPostsUseCase(useCaseImp: GetPostsUseCaseImp): GetPostUseCase

    @Binds
    abstract fun providesGetUsersUseCase(usersUseCaseImp: GetUsersUseCaseImp): GetUsersUseCase
}