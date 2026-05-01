package com.my.newsandroid.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.my.newsandroid.domain.model.Article

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val pubDate: String,
    val sourceDomain: String,
    val url: String,
    val page: Int
)

fun ArticleEntity.toDomain(): Article {
    return Article(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        pubDate = pubDate,
        sourceDomain = sourceDomain,
        url = url
    )
}

fun Article.toEntity(page: Int): ArticleEntity {
    return ArticleEntity(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        pubDate = pubDate,
        sourceDomain = sourceDomain,
        url = url,
        page = page
    )
}
