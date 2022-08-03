package com.defense.composetestapp.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@Deprecated("Use compose func")
abstract class BaseView<
        VM: BaseViewModel<VS, VE, VA>,
        VS: ViewState,
        VE: ViewEvent,
        VA: ViewAction
> {

    protected lateinit var viewModel: VM
    protected lateinit var navController: NavController

    @Composable
    fun Screen(navController: NavController) {
        this.navController = navController
        viewModel = getViewModelInstance()
        LaunchedEffect(key1 = Unit) {
            viewModel.onViewActive()
        }
        EventsProcessor()
        ScreenRoot()
    }

    @Composable
    protected abstract fun getViewModelInstance(): VM

    @Composable
    protected abstract fun Content(state: VS)

    protected abstract fun handleEvent(event: VE)

    protected fun postAction(action: VA) {
        viewModel.applyAction(action)
    }

    @Composable
    protected fun DefaultPreview() {
        val viewModel = getViewModelInstance()
        Content(viewModel.viewStateFlow.collectAsState().value)
    }

    @Composable
    private fun EventsProcessor() {
        LaunchedEffect(key1 = Unit) {
            viewModel.eventFlow.collectLatest { event ->
                event?.let { handleEvent(it) }
            }
        }
    }

    @Composable
    private fun ScreenRoot() {
        val viewState by viewModel.viewStateFlow.collectAsState()
        Content(viewState)
    }
}