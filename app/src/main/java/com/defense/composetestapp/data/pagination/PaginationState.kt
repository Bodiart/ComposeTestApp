package com.defense.composetestapp.data.pagination

sealed class PaginationState<Data> {
    data class LoadMore<Data>(val firstLoad: Boolean) : PaginationState<Data>()

    data class DataLoaded<Data>(val allData: Data, val onlyPageData: Data?) : PaginationState<Data>()

    class DataEmpty<Data> : PaginationState<Data>()

    class OnLoadMoreEnd<Data> : PaginationState<Data>()

    data class LoadError<Data>(val throwable: Throwable) : PaginationState<Data>()
}