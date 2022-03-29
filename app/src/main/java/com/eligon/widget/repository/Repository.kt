package com.eligon.widget.repository

import com.eligon.widget.api.RetrofitInstance
import com.eligon.widget.model.Record
import com.google.gson.JsonArray
import retrofit2.Response

class Repository {
    suspend fun getGold(): JsonArray {
        return  RetrofitInstance.api.getGold()
    }

    suspend fun getSilver(): JsonArray {
        return  RetrofitInstance.api.getSilver()
    }

    suspend fun getPlatinum(): JsonArray {
        return  RetrofitInstance.api.getPlatinum()
    }

    suspend fun getPalladium(): JsonArray {
        return  RetrofitInstance.api.getPalladium()
    }
}