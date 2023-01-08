package com.example.bookapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookapp.domain.model.XmlModel


@Dao
interface XmlDao {


    @Query("SELECT * FROM xml_table ORDER BY id ASC")
    fun getAllXmls(): PagingSource<Int, XmlModel>


    @Query("SELECT * FROM xml_table WHERE id=:xmlId")
    fun getSelectXml(xmlId: Int) : XmlModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addXmls(xml: List<XmlModel>)

    @Query("DELETE FROM xml_table")
    suspend fun deleteXml()
}