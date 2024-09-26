package com.example.news.domainlayer.usecase

import com.example.news.datalayer.model.APIResponse
import com.example.news.datalayer.util.Resource
import com.example.news.domainlayer.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(): Resource<APIResponse>{
        return newsRepository.getNewsHeadlines()
    }
}