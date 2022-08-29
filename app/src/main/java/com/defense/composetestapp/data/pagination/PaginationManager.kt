package com.defense.composetestapp.data.pagination

import com.defense.composetestapp.util.extensions.process


class PaginationManager<Key, Data>(
    private val config: PaginationConfig,
    private val source: PaginationSource<Key, Data>,
    private val onStateChanged: (PaginationState<Data>) -> Unit,
    private val loadMoreDetector: PaginationLoadMoreDetector = PaginationLoadMoreDetector(),
    private val initialKey: Key? = null
) {
    private var currentState: PaginationState<Data>? = null
    var currentData: Data? = null
        private set
    private var currentKey = initialKey

    suspend fun start() {
        currentKey = initialKey
        currentData = null
        loadData(firstLoad = true)
    }

    suspend fun listScrolled(lastVisibleItemPosition: Int, itemCount: Int) {
        if (currentState is PaginationState.LoadMore) { // don't load more if it already in progress
            return
        }
        if (loadMoreDetector.isLoadMore(lastVisibleItemPosition, itemCount)) {
            loadData()
        }
    }

    private suspend fun loadData(
        params: LoadParams<Key, Data> = getLoadParams(),
        firstLoad: Boolean = false
    ) {
        setState(PaginationState.LoadMore(firstLoad))
        source.load(params).process(
            successFunction = { result ->
                currentKey = result.nextKey
                currentData = result.allData
                setState(PaginationState.DataLoaded(result.allData, result.onlyPageData))
            },
            failureFunction = {
                setState(PaginationState.LoadError(it))
            }
        )
    }

    private fun getLoadParams() = LoadParams(currentKey, config.itemsPerPage, currentData)

    private fun setState(state: PaginationState<Data>) {
        currentState = state
        onStateChanged(state)
    }
}