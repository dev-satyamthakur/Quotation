package com.satyamthakur.quotify.networking

import com.satyamthakur.quotify.models.QuoteResponseItem
import com.satyamthakur.quotify.models.QuotesResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {
    @GET("/api/quotes")
    suspend fun getQuotes():
            Response<List<QuoteResponseItem>>
}