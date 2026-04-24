package com.preeti.newsapp.data.di.module

import com.preeti.newsapp.data.api.NetworkService
import com.preeti.newsapp.data.repository.TopHeadlineRepositoryImpl
import com.preeti.newsapp.domain.repository.TopHeadlineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTopHeadlineRepository(networkService: NetworkService): TopHeadlineRepository {
        return TopHeadlineRepositoryImpl(networkService)
    }

}