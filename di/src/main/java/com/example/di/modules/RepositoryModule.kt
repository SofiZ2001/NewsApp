package com.example.di.modules

import com.example.data.repository.NewsRepositoryImp
import com.example.domain.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideNewsRepository(repositoryImp: NewsRepositoryImp): NewsRepository

}