package com.my.newsandroid.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseDto(
    val data: NewsDataDto
)

@Serializable
data class NewsDataDto(
    val articles: ArticlesWrapperDto
)

@Serializable
data class ArticlesWrapperDto(
    val content: ArticlesContentDto
)

@Serializable
data class ArticlesContentDto(
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
