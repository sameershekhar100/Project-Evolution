package com.example.news.datalayer.repository

import com.example.news.datalayer.model.APIResponse
import com.example.news.datalayer.model.Article
import com.example.news.datalayer.repository.datasource.NewsRemoteDataSource
import com.example.news.datalayer.util.Resource
import com.example.news.domainlayer.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        return if (response.isSuccessful) {
            response.body()?.let { result ->
                Resource.Success(result)
            } ?: Resource.Error("Response Body is null ${response.message()}")
        } else
            Resource.Error(response.message())
    }

    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse> {
//        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: Article) {
//        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
//        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
//        TODO("Not yet implemented")
    }
}