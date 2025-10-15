package com.alonso.data.api

import com.alonso.data.model.CoffeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {
    @GET("/basaransuleyman/suleyman-basaranoglu-json/main/detail-page")
    suspend fun getCofferByCategory(): Response<List<CoffeeResponse>>
}