package com.my.newsandroid.data.repository

import com.my.newsandroid.data.remote.NewsApiService
import com.my.newsandroid.data.remote.toDomain
import com.my.newsandroid.domain.model.Article
import com.my.newsandroid.domain.repository.NewsRepository
import retrofit2.HttpException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService
) : NewsRepository {
    override suspend fun getNews(page: Int): List<Article> {
        return try {
            apiService.getNews(page.toString()).data.articles.content.page.map { it.toDomain() }
        } catch (e: HttpException) {
            if (e.code() == 404) {
                emptyList()
            } else {
                throw e
            }
        }
    }
}
