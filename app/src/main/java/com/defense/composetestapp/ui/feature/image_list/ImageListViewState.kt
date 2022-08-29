package com.defense.composetestapp.ui.feature.image_list

import com.defense.composetestapp.ui.base.ViewState

data class ImageListViewState(
    val items: List<Item>,
    val isLoadingVisible: Boolean
) : ViewState {

    sealed class Item {
        abstract val id: String

        data class Image(
            override val id: String,
            val imageUrl: String?,
            val counter: String
        ) : Item()

        class Loader(override val id: String) : Item()
    }
}