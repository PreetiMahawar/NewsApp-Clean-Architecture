package com.preeti.newsapp.data.di.module

import com.preeti.newsapp.data.repository.NewsSourcesRepositoryImpl
import com.preeti.newsapp.data.repository.TopHeadlineRepositoryImpl
import com.preeti.newsapp.domain.repository.NewsSourcesRepository
import com.preeti.newsapp.domain.repository.TopHeadlineRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTopHeadlineRepository(impl: TopHeadlineRepositoryImpl): TopHeadlineRepository

    @Binds
    @Singleton
    abstract fun bindNewsSourcesRepository(impl: NewsSourcesRepositoryImpl): NewsSourcesRepository

    /*@Provides
    @Singleton
    fun provideTopHeadlineRepository(networkService: NetworkService): TopHeadlineRepository {
        return TopHeadlineRepositoryImpl(networkService)
    }*/

    /*@Provides
    @Singleton
    fun provideNewsSourcesRepository(networkService: NetworkService): NewsSourcesRepository {
        return NewsSourcesRepositoryImpl(networkService)
    }*/
}