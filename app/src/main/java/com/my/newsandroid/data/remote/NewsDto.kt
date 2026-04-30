package com.my.newsandroid.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val data: NewsData
)

@Serializable
data class NewsData(
    val articles: ArticlesWrapper
)

@Serializable
data class ArticlesWrapper(
    val content: ArticlesContent
)

@Serializable
data class ArticlesContent(
    val page: List<ArticleDto>
)

@Serializable
data class ArticleDto(
    val articleId: String,
    val title: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val pubDate: String? = null,
    val sourceDomain: String? = null,
    val webContentUrl: String? = null
)
