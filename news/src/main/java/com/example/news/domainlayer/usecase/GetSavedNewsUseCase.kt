package com.example.news.domainlayer.usecase

import com.example.news.datalayer.model.Article
import com.example.news.domainlayer.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
     fun execute(): Flow<List<Article>>{
        return newsRepository.getSavedNews()
    }
}