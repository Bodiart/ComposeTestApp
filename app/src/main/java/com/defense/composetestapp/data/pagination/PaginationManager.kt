package com.defense.composetestapp.data.pagination


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
    private var prevPageData: Data? = null

    suspend fun start() {
        currentKey = initialKey
        prevPageData = null
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
        when (val result = source.load(params)) {
            is LoadResult.Data -> {
                currentKey = result.nextKey
                currentData = result.allData
                prevPageData = result.onlyPageData
                setState(PaginationState.DataLoaded(result.allData, result.onlyPageData))
            }
            is LoadResult.Error -> {
                setState(PaginationState.LoadError(result.throwable))
            }
        }
    }

    private fun getLoadParams() = LoadParams(currentKey, config.itemsPerPage, prevPageData)

    private fun setState(state: PaginationState<Data>) {
        currentState = state
        onStateChanged(state)
    }
}