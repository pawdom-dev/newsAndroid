package com.my.newsandroid.domain.repository

import com.my.newsandroid.domain.model.Article

interface NewsRepository {
    suspend fun getNews(page: Int): List<Article>
}
