package com.satyamthakur.quotify.networking

import com.satyamthakur.quotify.models.QuotesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {
    @GET("/quotes/random")
    suspend fun getQuotes(@Query("limit") limit: Int, @Query("page") page: Int): Response<QuotesResponse>
}