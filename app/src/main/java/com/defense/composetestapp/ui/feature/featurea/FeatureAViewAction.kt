package com.defense.composetestapp.ui.feature.featurea

import com.defense.composetestapp.ui.base.ViewAction

sealed class FeatureAViewAction : ViewAction

object Button1ClickedAction : FeatureAViewAction()

object Button2ClickedAction :  FeatureAViewAction()