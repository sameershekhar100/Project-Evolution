package com.example.news.domainlayer.usecase

import com.example.news.domainlayer.repository.NewsRepository

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
}