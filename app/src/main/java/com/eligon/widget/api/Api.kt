package com.eligon.widget.api

import com.eligon.widget.model.Record
import com.google.gson.JsonArray
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("gold")
    suspend fun getGold(): JsonArray

    @GET("silver")
    suspend fun getSilver(): JsonArray

    @GET("platinum")
    suspend fun getPlatinum(): JsonArray

    @GET("palladium")
    suspend fun getPalladium(): JsonArray
}