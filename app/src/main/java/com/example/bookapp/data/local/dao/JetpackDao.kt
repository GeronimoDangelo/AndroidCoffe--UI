package com.example.bookapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookapp.domain.model.Jetpack

@Dao
interface JetpackDao {
    @Query("SELECT * FROM jetpack_table ORDER BY id ASC")
    fun getAllJetpacks(): PagingSource<Int, Jetpack>

    @Query("SELECT * FROM jetpack_table WHERE id=:jetId")
    fun getSelectedJetpack(jetId: Int): Jetpack

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJetpack(jetpacks: List<Jetpack>)

    @Query("DELETE FROM jetpack_table")
    suspend fun deleteAllJetpacks()
}