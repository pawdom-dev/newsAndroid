package com.my.newsandroid.domain.model

data class Article(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val pubDate: String,
    val sourceDomain: String,
    val url: String
)
