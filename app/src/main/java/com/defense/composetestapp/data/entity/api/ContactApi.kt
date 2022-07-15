package com.defense.composetestapp.data.entity.api

import com.google.gson.annotations.SerializedName

data class ContactApi(
    @SerializedName("name")
    val name: NameApi?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("picture")
    val picture: PictureApi?
)

data class NameApi(
    @SerializedName("first")
    val first: String?,
    @SerializedName("last")
    val last: String?
)

data class PictureApi(
    @SerializedName("large")
    val large: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?
)