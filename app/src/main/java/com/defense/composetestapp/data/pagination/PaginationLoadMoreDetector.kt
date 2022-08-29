package com.defense.composetestapp.data.pagination

private const val ITEMS_TO_LOAD_MORE = 2

open class PaginationLoadMoreDetector {

    open fun isLoadMore(lastVisibleItemPosition: Int, itemCount: Int): Boolean {
        return itemCount - lastVisibleItemPosition <= ITEMS_TO_LOAD_MORE
    }
}