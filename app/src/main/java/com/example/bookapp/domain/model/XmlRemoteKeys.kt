package com.example.bookapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookapp.util.Constants.XML_REMOTE_KEYS_DATABASE_TABLE
import kotlinx.serialization.Serializable


@Entity(tableName = XML_REMOTE_KEYS_DATABASE_TABLE)
@Serializable
data class XmlRemoteKeys (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
        )
