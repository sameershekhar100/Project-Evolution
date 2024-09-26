package com.example.news.domainlayer.usecase

import com.example.news.datalayer.model.APIResponse
import com.example.news.datalayer.util.Resource
import com.example.news.domainlayer.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery: String): Resource<APIResponse> {
        return newsRepository.getSearchedNews(searchQuery)
    }
}