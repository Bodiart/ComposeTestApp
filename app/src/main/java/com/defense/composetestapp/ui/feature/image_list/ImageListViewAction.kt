package com.defense.composetestapp.ui.feature.image_list

import com.defense.composetestapp.ui.base.ViewAction

sealed class ImageListViewAction : ViewAction

data class ListScrolledAction(val lastVisibleItemPosition: Int?, val itemCount: Int) : ImageListViewAction()