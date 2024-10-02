package com.example.news.presentationlayer.di

import com.example.news.domainlayer.repository.NewsRepository
import com.example.news.domainlayer.usecase.GetNewsHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetNewsHeadLineUseCase(newsRepository: NewsRepository): GetNewsHeadlinesUseCase {
        return GetNewsHeadlinesUseCase(newsRepository)
    }

//    @Provides
//    @Singleton
//    fun provideNewsViewModelFactory(
//        application: Application,
//        newsHeadlinesUseCase: GetNewsHeadlinesUseCase
//    ): NewsViewModelFactory {
//        return NewsViewModelFactory(application, newsHeadlinesUseCase)
//    }
}