package com.defense.composetestapp.data.pagination

interface PaginationSource<Key, Data> {
    suspend fun load(params: LoadParams<Key, Data>): LoadResult<Key, Data>
}

data class LoadParams<Key, Data>(
    val key: Key?,
    val perPage: Int,
    val prevPageData: Data?
)

sealed class LoadResult<Key, Data>{
    data class Error<Key, Data>(val throwable: Throwable) : LoadResult<Key, Data>()

    data class Data<Key, Data>(
        val allData: Data,
        val nextKey: Key,
        val onlyPageData: Data? = null
    ) : LoadResult<Key, Data>()
}