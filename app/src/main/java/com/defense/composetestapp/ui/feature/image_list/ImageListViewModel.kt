package com.defense.composetestapp.ui.feature.image_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.defense.composetestapp.data.entity.data.image.UnsplashImage
import com.defense.composetestapp.data.pagination.PaginationConfig
import com.defense.composetestapp.data.pagination.PaginationManager
import com.defense.composetestapp.data.pagination.PaginationState
import com.defense.composetestapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 10
private const val INITIAL_PAGE = 1

@HiltViewModel
class ImageListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val imageListPaginationSource: ImageListPaginationSource
) : BaseViewModel<ImageListViewState, ImageListViewEvent, ImageListViewAction>(savedStateHandle) {

    private val paginationManager = PaginationManager(
        PaginationConfig(
            ITEMS_PER_PAGE
        ),
        source = imageListPaginationSource,
        onStateChanged = ::handlePaginationStateChanged,
        initialKey = INITIAL_PAGE
    )

    init {
        mutableViewStateFlow.value = ImageListViewState(
            items = listOf(),
            isLoadingVisible = true
        )

        viewModelScope.launch {
            paginationManager.initializePagination()
        }
    }

    override fun handleAction(action: ImageListViewAction) {
        when (action) {
            is ListScrolledAction -> viewModelScope.launch {
                paginationManager.listScrolled(action.lastVisibleItemPosition ?: 0, action.itemCount)
            }
        }
    }

    private fun handlePaginationStateChanged(state: PaginationState<ImageData>) {
        println("ImageListViewModel state=$state")
        updateState { copy(isLoadingVisible = state is PaginationState.LoadMore) }
        when (state) {
            is PaginationState.DataLoaded -> refreshImageList()
        }
    }

    private fun refreshImageList() {
        updateState {
            copy(
                items = makeImageAdapterList(paginationManager.currentData)
            )
        }
    }

    private fun makeImageAdapterList(data: ImageData?): List<ImageListViewState.Item> {
        data ?: return emptyList()
        return data.imageList.map {
            ImageListViewState.Item.Image(
                it.id,
                it.url,
                it.counter.toString()
            )
        }
    }
}

data class ImageData(
    val imageList: List<UnsplashImage>
)