package com.example.news.datalayer.repository.datasource

import com.example.news.datalayer.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country:String,page:Int): Response<APIResponse>
}