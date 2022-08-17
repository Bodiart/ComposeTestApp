package com.defense.composetestapp.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.defense.composetestapp.ui.navigation.NavAction
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <
        VM : BaseViewModel<VS, VE, VA>,
        VS : ViewState,
        VE : ViewEvent,
        VA : ViewAction> BaseScreen(
    vm: VM,
    handleEvent: (VE) -> Unit,
    navController: NavController?,
    content: @Composable (state: VS, postAction: (VA) -> Unit) -> Unit
) {
    EventsProcessor(vm, handleEvent)
    RouteProcessor(vm, navController)
    vm.viewStateFlow.collectAsState().value?.let { state ->
        content(state) { action ->
            vm.applyAction(action)
        }
    }
}

@Composable
fun <
        VM : BaseViewModel<VS, VE, VA>,
        VS : ViewState,
        VE : ViewEvent,
        VA : ViewAction> EventsProcessor(vm: VM, handleEvent: (VE) -> Unit) {
    LaunchedEffect(key1 = Unit) {
        vm.eventFlow.collectLatest { event ->
            event?.let { handleEvent(it) }
        }
    }
}

@Composable
fun <
        VM : BaseViewModel<VS, VE, VA>,
        VS : ViewState,
        VE : ViewEvent,
        VA : ViewAction> RouteProcessor(vm: VM, navController: NavController?) {
    LaunchedEffect(key1 = Unit) {
        vm.routeFlow.collectLatest { event ->
            event?.let {
                navController?.navigate(it)
            }
        }
    }
}

fun NavController.navigate(navAction: NavAction) {
    when (navAction) {
        is NavAction.Navigate -> navigate(navAction.route)
        NavAction.PopBackStack -> popBackStack()
        NavAction.NavigateUp -> navigateUp()
    }
}