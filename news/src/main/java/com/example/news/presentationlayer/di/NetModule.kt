package com.example.news.presentationlayer.di

import com.example.news.BuildConfig
import com.example.news.datalayer.api.NewsAPIService
import com.example.news.datalayer.repository.NewsRepositoryImpl
import com.example.news.datalayer.repository.datasource.NewsRemoteDataSource
import com.example.news.datalayer.repository.datasourceImpl.NewsRemoteDataSourceImpl
import com.example.news.domainlayer.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsAPIService(retrofit: Retrofit): NewsAPIService {
        return retrofit.create(NewsAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsAPIService: NewsAPIService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsAPIService)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsRemoteDataSource: NewsRemoteDataSource): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource)
    }
}