@file:OptIn(ExperimentalAnimationApi::class)

package com.defense.composetestapp.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.defense.composetestapp.ui.theme.ComposeTestAppTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.flow.collectLatest

abstract class BaseActivity<
        VM: BaseViewModel<VS, VE, VA>,
        VS: ViewState,
        VE: ViewEvent,
        VA: ViewAction
> : ComponentActivity() {

    protected lateinit var viewModel: VM
    protected lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberAnimatedNavController()
            ComposeTestAppTheme {
                Screen()
            }
        }
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
        viewModel.viewStateFlow.collectAsState().value?.let {
            Content(it)
        }
    }

    @Composable
    private fun Screen() {
        viewModel = getViewModelInstance()
        LaunchedEffect(key1 = Unit) {
            viewModel.onViewActive()
        }
        EventsProcessor()
        ScreenRoot()
        NavigationComponent()
    }

    @Composable
    abstract fun NavigationComponent()

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
        viewModel.viewStateFlow.collectAsState().value?.let {
            Content(it)
        }
    }
}