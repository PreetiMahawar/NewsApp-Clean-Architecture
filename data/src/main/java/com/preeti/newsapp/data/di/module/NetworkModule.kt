package com.preeti.newsapp.data.di.module

import com.preeti.newsapp.data.api.ApiKeyInterceptor
import com.preeti.newsapp.data.api.NetworkService
import com.preeti.newsapp.data.di.BaseUrl
import com.preeti.newsapp.data.di.NetworkApiKey
import com.preeti.newsapp.data.dispatcher.DefaultDispatcherProvider
import com.preeti.newsapp.domain.dispacther.DispatcherProvider
import com.preeti.newsapp.utils.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @NetworkApiKey
    @Provides
    fun provideApiKey(): String = AppConstant.API_KEY

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(@NetworkApiKey apiKey: String): ApiKeyInterceptor =
        ApiKeyInterceptor(apiKey)

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(apiKeyInterceptor).build()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

}