package com.defense.composetestapp.ui.feature.navigationpattern.screena

import com.defense.composetestapp.ui.base.ViewAction

sealed class ScreenAViewAction : ViewAction

object Button1ClickedAction : ScreenAViewAction()

object Button2ClickedAction : ScreenAViewAction()