package com.my.newsandroid.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Query("SELECT * FROM articles ORDER BY page ASC, pubDate DESC")
    suspend fun getAllArticles(): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE page = :page ORDER BY pubDate DESC")
    suspend fun getArticlesByPage(page: Int): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles")
    suspend fun clearAll()
}
