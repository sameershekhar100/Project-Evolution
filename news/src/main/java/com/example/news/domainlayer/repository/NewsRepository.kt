package com.example.news.domainlayer.repository

import com.example.news.datalayer.model.APIResponse
import com.example.news.datalayer.model.Article
import com.example.news.datalayer.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(country:String,page:Int): Resource<APIResponse>
    suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}