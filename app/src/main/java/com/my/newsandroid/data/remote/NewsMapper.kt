package com.my.newsandroid.data.remote

import com.my.newsandroid.domain.model.Article

fun ArticleDto.toDomain(): Article {
    return Article(
        id = articleId,
        title = title,
        description = description ?: "",
        imageUrl = imageUrl ?: "",
        pubDate = pubDate ?: "",
        sourceDomain = sourceDomain ?: "",
        url = webContentUrl ?: ""
    )
}
