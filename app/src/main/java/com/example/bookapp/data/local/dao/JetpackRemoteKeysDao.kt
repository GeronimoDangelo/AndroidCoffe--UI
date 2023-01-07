package com.example.bookapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookapp.domain.model.JetpackRemoteKeys

@Dao
interface JetpackRemoteKeysDao {

    @Query("SELECT * FROM jetpack_remote_keys_table WHERE id =:jetpackId")
    suspend fun getRemoteKeys(jetpackId: Int): JetpackRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(jetpackRemoteKeys: List<JetpackRemoteKeys>)

    @Query("DELETE FROM jetpack_remote_keys_table")
    suspend fun deleteAllRemoteKeys()

}