package com.coffee.bookapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coffee.bookapp.util.Constants
import kotlinx.serialization.Serializable

@Entity(tableName = Constants.JETPACK_DATABASE_TABLE)
@Serializable
data class Jetpack(
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
