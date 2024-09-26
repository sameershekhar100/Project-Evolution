package com.example.news.domainlayer.usecase

import com.example.news.domainlayer.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
}