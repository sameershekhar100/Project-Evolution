package com.example.news.domainlayer.usecase

import com.example.news.domainlayer.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
}