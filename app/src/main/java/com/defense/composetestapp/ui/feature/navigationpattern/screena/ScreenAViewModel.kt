package com.defense.composetestapp.ui.feature.navigationpattern.screena

import com.defense.composetestapp.di.module.DI_NAME_NAVIGATION_MAIN
import com.defense.composetestapp.ui.base.BaseViewModel
import com.defense.composetestapp.ui.feature.main.navigation.MainScreen
import com.defense.composetestapp.ui.feature.main.navigation.MainScreenRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ScreenAViewModel @Inject constructor(
    @Named(DI_NAME_NAVIGATION_MAIN) private val router: MainScreenRouter
) : BaseViewModel<ScreenAViewState, ScreenAViewEvent, ScreenAViewAction>() {

    override fun createInitialState() = ScreenAViewState(
        button1 = "button 1",
        button2 = "button 2"
    )

    override fun handleAction(action: ScreenAViewAction) {
        when (action) {
            Button1ClickedAction -> handleButton1Clicked()
            Button2ClickedAction -> handleButton2Clicked()
        }
    }

    private fun handleButton1Clicked() {
        router.navigate(MainScreen.ScreenB("button1arg"))
    }

    private fun handleButton2Clicked() {
        router.navigate(MainScreen.ScreenB("button2arg"))
    }
}