package com.example.news.datalayer.repository.datasourceImpl

import com.example.news.datalayer.api.NewsAPIService
import com.example.news.datalayer.model.APIResponse
import com.example.news.datalayer.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService
):NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country:String,page:Int): Response<APIResponse> {
        return newsAPIService.getTopHeadlines(country,page)
    }
}