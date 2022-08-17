package com.defense.composetestapp.ui.feature.main

import androidx.lifecycle.SavedStateHandle
import com.defense.composetestapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): BaseViewModel<
        MainViewState, MainViewEvent, MainViewAction
>(savedStateHandle) {

    init {
        mutableViewStateFlow.value = MainViewState()
    }

    override fun handleAction(action: MainViewAction) {
    }

    override fun onViewActive() {
    }
}