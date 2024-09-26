package com.example.news.domainlayer.usecase

import com.example.news.datalayer.model.Article
import com.example.news.domainlayer.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) {
        return newsRepository.deleteNews(article)
    }
}