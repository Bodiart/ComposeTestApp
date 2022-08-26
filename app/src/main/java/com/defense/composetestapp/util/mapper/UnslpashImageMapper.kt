package com.defense.composetestapp.util.mapper

import com.defense.composetestapp.data.entity.api.unsplash.UnsplashImageApi
import com.defense.composetestapp.data.entity.data.image.UnsplashImage

fun UnsplashImageApi.toData(): UnsplashImage? {
    return UnsplashImage(
        this.id ?: return null,
        this.urls?.regular
    )
}

fun List<UnsplashImageApi>.toData() = this.mapNotNull { it.toData() }