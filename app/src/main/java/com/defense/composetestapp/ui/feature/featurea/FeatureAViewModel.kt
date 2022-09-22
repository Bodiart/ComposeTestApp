package com.defense.composetestapp.ui.feature.featurea

import androidx.lifecycle.SavedStateHandle
import com.defense.composetestapp.ui.base.BaseViewModel
import com.defense.composetestapp.ui.feature.featureb.FeatureBArgs
import com.defense.composetestapp.ui.feature.main.MainDestination
import com.defense.composetestapp.ui.navigation.NavAction
import com.defense.composetestapp.ui.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeatureAViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<FeatureAViewState, FeatureAViewEvent, FeatureAViewAction>(savedStateHandle) {

    init {
        mutableViewStateFlow.value = FeatureAViewState()
    }

    override fun handleAction(action: FeatureAViewAction) {
        when (action) {
            Button1ClickedAction -> navigate(
                NavAction.Navigate(
                    NavRoute(
                        MainDestination.FeatureB,
                        FeatureBArgs("button 1")
                    )
                )
            )
            Button2ClickedAction -> navigate(
                NavAction.Navigate(
                    NavRoute(
                        MainDestination.FeatureB,
                        FeatureBArgs("button 2")
                    )
                )
            )
        }
    }
}