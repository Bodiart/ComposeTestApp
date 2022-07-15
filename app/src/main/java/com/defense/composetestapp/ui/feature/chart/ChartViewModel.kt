package com.defense.composetestapp.ui.feature.chart

import androidx.lifecycle.viewModelScope
import com.defense.composetestapp.data.entity.data.Candle
import com.defense.composetestapp.data.usecase.QuotesGetUseCase
import com.defense.composetestapp.ui.base.BaseViewModel
import com.defense.composetestapp.util.extensions.process
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val quotesGetUseCase: QuotesGetUseCase
) : BaseViewModel<ChartViewState, ChartViewEvent, ChartViewAction>() {

    private var chartCandles = listOf<Candle>()

    init {
        setupChart()
    }

    override fun createInitialState() = ChartViewState(
        isProgressVisible = true,
        isChartVisible = false,
        chart = listOf()
    )

    override fun handleAction(action: ChartViewAction) {
    }

    private fun setupChart() = viewModelScope.launch {
        quotesGetUseCase.perform().process(
            {
                chartCandles = it
                updateState { copy(isChartVisible = true) }
                refreshChart()
            },
            {

            },
            {
                updateState { copy(isProgressVisible = false) }
            }
        )
    }

    private fun refreshChart() {
        updateState { copy(chart = chartCandles) }
    }
}