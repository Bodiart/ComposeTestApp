//package com.defense.composetestapp.ui.feature.old_base_view.chart
//
//import androidx.compose.runtime.Composable
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.defense.composetestapp.ui.base.BaseView
//import com.defense.composetestapp.ui.custom.AnimatedFullScreenProgress
//import com.defense.composetestapp.ui.custom.chart.MarketChart
//
//class ChartScreen : BaseView<ChartViewModel, ChartViewState, ChartViewEvent, ChartViewAction>() {
//
//    @Composable
//    override fun getViewModelInstance(): ChartViewModel = hiltViewModel()
//
//    @Composable
//    override fun Content(state: ChartViewState) {
//        AnimatedFullScreenProgress(isVisible = state.isProgressVisible)
//        if (state.isChartVisible) {
//            MarketChart(candles = state.chart)
//        }
//    }
//
//    override fun handleEvent(event: ChartViewEvent) {
//    }
//}