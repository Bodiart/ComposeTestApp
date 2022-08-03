package com.defense.composetestapp.ui.feature.navigationpattern.screenb

import androidx.lifecycle.SavedStateHandle
import com.defense.composetestapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenBViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<
        ScreenBViewState,
        ScreenBViewEvent,
        ScreenBViewAction
>() {

    private val name = requireNotNull(savedStateHandle.get<String?>("name"))

    init {
        println()
    }

    override fun createInitialState() = ScreenBViewState(
        name = ""
    )

    override fun handleAction(action: ScreenBViewAction) {
        TODO("Not yet implemented")
    }
}