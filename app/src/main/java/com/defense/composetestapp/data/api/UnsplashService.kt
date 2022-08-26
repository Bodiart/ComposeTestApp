package com.defense.composetestapp.data.api

import com.defense.composetestapp.data.entity.api.unsplash.UnsplashImageApi
import retrofit2.http.*

private const val ACCESS_KEY = "N4qg65KPPEM5WQmfiI85ho8b2QDB0jYDVJQiiljr80U"
private const val SECRET_KEY = "DQuE7Wxjp2fyOOfOZkWz1ChIEpPtG7jkQ-zoEfgElOY"

interface UnsplashService {

    @GET("photos")
    @Headers(
        "Accept-Version: v1"
    )
    suspend fun getPhotos(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("client_id") clientId: String = ACCESS_KEY
    ): List<UnsplashImageApi>
}