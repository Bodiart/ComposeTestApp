package com.defense.composetestapp.ui.feature.main

import com.defense.composetestapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel<
        MainViewState, MainViewEvent, MainViewAction
>() {

    override fun createInitialState() = MainViewState()

    override fun handleAction(action: MainViewAction) {
    }

    override fun onViewActive() {
    }
}