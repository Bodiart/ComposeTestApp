@file:OptIn(ExperimentalAnimationApi::class)

package com.defense.composetestapp.ui.feature.main

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.defense.composetestapp.di.module.DI_NAME_NAVIGATION_MAIN
import com.defense.composetestapp.ui.base.BaseActivity
import com.defense.composetestapp.ui.feature.main.navigation.MainNavigatorHolder
import com.defense.composetestapp.ui.feature.main.navigation.MainScreen
import com.defense.composetestapp.ui.feature.main.navigation.MainScreenDescription
import com.defense.composetestapp.ui.feature.main.navigation.MainScreenNavigator
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : BaseActivity<
        MainViewModel,
        MainViewState,
        MainViewEvent,
        MainViewAction
>() {

    private lateinit var navigator: MainScreenNavigator

    @Named(DI_NAME_NAVIGATION_MAIN)
    @Inject
    lateinit var navigatorHolder: MainNavigatorHolder

    @Composable
    override fun getViewModelInstance(): MainViewModel = hiltViewModel()

    @Composable
    override fun Content(state: MainViewState) {

    }

    override fun handleEvent(event: MainViewEvent) {
    }

    @Composable
    override fun NavigationComponent() {
        val springSpec = spring<IntOffset>(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessLow
        )

        navigator = MainScreenNavigator(navController)
        navigatorHolder.setNavigator(navigator)

        AnimatedNavHost(
            navController = navController,
            startDestination = MainScreenDescription.SCREEN_A.buildRouteWithPlaceholders()
        ) {
            composable(
                route = MainScreenDescription.SCREEN_A.buildRouteWithPlaceholders(),
                argNames = MainScreenDescription.SCREEN_A.args,
                springSpec = springSpec
            ) {
                MainScreen.ScreenA.ScreenRoot()
            }
            composable(
                route = MainScreenDescription.SCREEN_B.buildRouteWithPlaceholders(),
                argNames = MainScreenDescription.SCREEN_B.args,
                springSpec = springSpec
            ) { backStackEntry ->
                MainScreen.ScreenB(
                    backStackEntry.arguments?.getString(MainScreenDescription.SCREEN_B.args[0].name)!!
                ).ScreenRoot()
            }
        }
    }

    fun NavGraphBuilder.composable(
        route: String,
        argNames: List<NamedNavArgument>,
        springSpec: SpringSpec<IntOffset>,
        content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
    ) {
        composable(
            route = route,
            arguments = argNames,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = springSpec)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { it * -1 }, animationSpec = springSpec)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { it * -1 }, animationSpec = springSpec)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = springSpec)
            },
            content = content
        )
    }

    @Composable
    @Preview
    fun ContentPreview() {
        DefaultPreview()
    }
}