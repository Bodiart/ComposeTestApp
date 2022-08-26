package com.defense.composetestapp.ui.feature.image_list

import com.defense.composetestapp.data.api.UnsplashService
import com.defense.composetestapp.data.pagination.LoadParams
import com.defense.composetestapp.data.pagination.LoadResult
import com.defense.composetestapp.data.pagination.PaginationSource
import com.defense.composetestapp.util.mapper.toData
import kotlinx.coroutines.delay
import java.lang.RuntimeException
import javax.inject.Inject

class ImageListPaginationSource @Inject constructor(
    private val unsplashService: UnsplashService
) : PaginationSource<Int, ImageData> {

    override suspend fun load(params: LoadParams<Int, ImageData>): LoadResult<Int, ImageData> {
        return try {
            if (params.key == null) {
                throw RuntimeException("For this pagination key shouldn't be null")
            }
            delay(1000)
            val response = unsplashService.getPhotos(params.key, params.perPage)
            val imageList = response.toData()
            LoadResult.Data(
                allData = params.prevPageData?.let {
                    it.copy(
                        imageList = it.imageList.toMutableList().apply { addAll(imageList) }
                    )
                } ?: ImageData(imageList),
                nextKey = params.key + 1,
                onlyPageData = ImageData(imageList)
            )
        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }
}