package com.example.sqlroompractice.dao

import androidx.room.*
import com.example.sqlroompractice.entity.News

@Dao
interface NewsDao {

    @Query("select * from news")
    fun getAllNews(): List<News>

    @Insert
    fun addNews(news: News)

    @Delete
    fun deleteNews(news: News)

    @Update
    fun updateNews(news: News)

    @Query("select * from news where id =:id")
    fun getNewById(id: Int): News

    @Query("select id from news where news_title = :title and description =:desc")
    fun getNewById(title: String, desc: String): Int

    @Insert
    fun addAllNews(vararg news: News)
}