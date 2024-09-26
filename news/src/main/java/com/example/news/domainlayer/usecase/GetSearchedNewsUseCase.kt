package com.example.news.domainlayer.usecase

import com.example.news.domainlayer.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
}