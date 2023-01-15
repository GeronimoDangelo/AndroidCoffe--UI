package com.coffee.bookapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coffee.bookapp.util.Constants.JETPACK_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = JETPACK_REMOTE_KEYS_DATABASE_TABLE)
data class JetpackRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
