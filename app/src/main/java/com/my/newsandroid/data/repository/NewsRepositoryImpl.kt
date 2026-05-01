package com.my.newsandroid.data.repository

import com.my.newsandroid.data.local.NewsDao
import com.my.newsandroid.data.local.toDomain as localToDomain
import com.my.newsandroid.data.local.toEntity
import com.my.newsandroid.data.remote.NewsApiService
import com.my.newsandroid.data.remote.toDomain as remoteToDomain
import com.my.newsandroid.domain.model.Article
import com.my.newsandroid.domain.repository.NewsRepository
import retrofit2.HttpException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService,
    private val newsDao: NewsDao
) : NewsRepository {
    override suspend fun getNews(page: Int): List<Article> {
        return try {
            val networkResponse = apiService.getNews(page.toString())
            val articles = networkResponse.data.articles.content.page.map { it.remoteToDomain() }
            
            // Sync to local database
            newsDao.insertArticles(articles.map { it.toEntity(page) })
            
            articles
        } catch (e: Exception) {
            val cachedArticles = newsDao.getArticlesByPage(page)
            if (cachedArticles.isNotEmpty()) {
                cachedArticles.map { it.localToDomain() }
            } else {
                if (e is HttpException && e.code() == 404) {
                    emptyList()
                } else {
                    throw e
                }
            }
        }
    }
}
