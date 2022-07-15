package com.defense.composetestapp.data.entity.api

import com.google.gson.annotations.SerializedName

data class ApiContactResponse(
    @SerializedName("results")
    val results: List<ContactApi>?
)