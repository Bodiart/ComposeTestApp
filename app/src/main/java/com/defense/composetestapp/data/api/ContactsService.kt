package com.defense.composetestapp.data.api

import com.defense.composetestapp.data.entity.api.ApiContactResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactsService {
    @GET("api/")
    suspend fun getContacts(@Query("results") limit: Int = 30): ApiContactResponse
}