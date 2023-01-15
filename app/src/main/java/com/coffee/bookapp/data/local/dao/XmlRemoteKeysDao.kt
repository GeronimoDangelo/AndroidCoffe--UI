package com.coffee.bookapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coffee.bookapp.domain.model.XmlRemoteKeys

@Dao
interface XmlRemoteKeysDao {

    @Query("SELECT * FROM xml_remote_keys_table WHERE id=:xmlId")
    suspend fun getRemoteKeys(xmlId: Int): XmlRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(xmlRemoteKeys: List<XmlRemoteKeys>)

    @Query("DELETE FROM xml_remote_keys_table")
    suspend fun deleteAllRemoteKeys()


}