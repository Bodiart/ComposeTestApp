package com.defense.composetestapp.ui.feature.featureb

import androidx.lifecycle.SavedStateHandle
import com.defense.composetestapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeatureBViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<FeatureBViewState, FeatureBViewEvent, FeatureBViewAction>(savedStateHandle) {

    private val args = navArgs<FeatureBArgs>()

    init {
        mutableViewStateFlow.value = FeatureBViewState(
            text = args.text
        )
    }

    override fun handleAction(action: FeatureBViewAction) {
    }
}

