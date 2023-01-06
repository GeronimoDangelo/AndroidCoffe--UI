package com.example.bookapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookapp.util.Constants.BOOK_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = BOOK_DATABASE_TABLE)
data class Book(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val level: String,
    val timeToLearn: String,
    val tags: List<String>,
)
