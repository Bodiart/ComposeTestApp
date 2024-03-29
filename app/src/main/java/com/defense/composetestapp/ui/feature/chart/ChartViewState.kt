package com.defense.composetestapp.ui.feature.chart

import com.defense.composetestapp.data.entity.data.Candle
import com.defense.composetestapp.ui.base.ViewState

data class ChartViewState(
    val isProgressVisible: Boolean,
    val isChartVisible: Boolean,
    val chart: List<Candle>,
) : ViewState