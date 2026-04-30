package com.my.newsandroid.data.repository

import com.my.newsandroid.data.remote.NewsApiService
import com.my.newsandroid.data.remote.toDomain
import com.my.newsandroid.domain.model.Article
import com.my.newsandroid.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService
) : NewsRepository {
    override suspend fun getNews(): List<Article> {
        return apiService.getNews("1").data.articles.content.page.map { it.toDomain() }
    }
}
