package com.example.sqlroompractice.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class News {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "news_title")
    var title: String? = null

    @ColumnInfo(name = "description")
    var desc: String? = null

}
