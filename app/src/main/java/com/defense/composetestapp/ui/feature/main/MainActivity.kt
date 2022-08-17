@file:OptIn(ExperimentalAnimationApi::class)

package com.defense.composetestapp.ui.feature.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import com.defense.composetestapp.ui.base.BaseActivity
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<
        MainViewModel,
        MainViewState,
        MainViewEvent,
        MainViewAction
>() {

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
        AnimatedNavHost(
            navController = navController,
            startDestination = MainDestination::class.sealedSubclasses
                    .mapNotNull { it.objectInstance }
                    .first()
                    .routeWithPlaceholders()
        ) {
            MainDestination::class.sealedSubclasses
                    .mapNotNull { it.objectInstance }
                    .map {
                        composable(
                                it.routeWithPlaceholders(),
                                arguments = it.argNames(),
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
                                content =  { backStackEntry ->
                                    it.Screen(navController)
                                }
                        )
                    }
//            NavigationScreen.values().map { screen ->
//                composable(
//                    screen.templateRoute(),
//                    arguments = screen.argNames(),
//                    enterTransition = {
//                        slideInHorizontally(initialOffsetX = { it }, animationSpec = springSpec)
//                    },
//                    exitTransition = {
//                        slideOutHorizontally(targetOffsetX = { it * -1 }, animationSpec = springSpec)
//                    },
//                    popEnterTransition = {
//                        slideInHorizontally(initialOffsetX = { it * -1 }, animationSpec = springSpec)
//                    },
//                    popExitTransition = {
//                        slideOutHorizontally(targetOffsetX = { it }, animationSpec = springSpec)
//                    },
//                    content =  { backStackEntry ->
//                        screen.Screen(
//                            navController,
//                            backStackEntry.arguments
//                        )
//                    }
//                )
//            }
        }
    }

    @Composable
    @Preview
    fun ContentPreview() {
        DefaultPreview()
    }
}