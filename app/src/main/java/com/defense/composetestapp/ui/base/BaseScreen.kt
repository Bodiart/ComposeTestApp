package com.defense.composetestapp.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <
        VM : BaseViewModel<VS, VE, VA>,
        VS : ViewState,
        VE : ViewEvent,
        VA : ViewAction> BaseScreen(
    vm: VM,
    handleEvent: (VE) -> Unit,
    navController: NavController? = null,
    content: @Composable (state: VS, postAction: (VA) -> Unit) -> Unit
) {
    EventsProcessor(vm, handleEvent, navController)
    content(vm.viewStateFlow.collectAsState().value) {
        vm.applyAction(it)
    }
}

@Composable
fun <
        VM : BaseViewModel<VS, VE, VA>,
        VS : ViewState,
        VE : ViewEvent,
        VA : ViewAction> EventsProcessor(
    vm: VM,
    handleEvent: (VE) -> Unit,
    navController: NavController?
) {
    LaunchedEffect(key1 = Unit) {
        vm.eventFlow.collectLatest { event ->
            event?.let { handleEvent(it) }
        }
        vm.routeFlow.collectLatest { route ->
            route?.let {
                navController?.navigate(route)
            }
        }
    }
}