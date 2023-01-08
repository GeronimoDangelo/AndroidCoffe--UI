package com.example.bookapp.data.remote

import com.example.bookapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("/book/learn")
    suspend fun getAllBooks(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/book/learn/search")
    suspend fun searchBooks(
        @Query("name") name: String
    ): ApiResponse

    @GET("/book/jetpacks")
    suspend fun getAllJetpacks(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/book/jetpacks/search")
    suspend fun searchJetpack(
        @Query("name") name: String
    ): ApiResponse

    @GET("/book/xml")
    suspend fun getAllXmls(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/book/xml/search")
    suspend fun searchXml(
        @Query("name") name: String
    ): ApiResponse

}