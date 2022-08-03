package com.defense.composetestapp.ui.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.defense.composetestapp.di.navigation.NavScreen
import com.defense.composetestapp.di.navigation.ScreenDescription
import com.defense.composetestapp.di.navigation.buildRouteWithArgPlaceholders
import com.defense.composetestapp.di.navigation.buildRouteWithArgs
import com.defense.composetestapp.ui.feature.navigationpattern.screena.ScreenAScreen
import com.defense.composetestapp.ui.feature.navigationpattern.screenb.ScreenBScreen

sealed class MainScreen : NavScreen() {

    object ScreenA : MainScreen() {

        override val screenDescription = MainScreenDescription.SCREEN_A

        @Composable
        override fun ScreenRoot() {
            ScreenAScreen()
        }
    }

    data class ScreenB(val name: String) : MainScreen() {

        override val screenDescription = MainScreenDescription.SCREEN_B

        @Composable
        override fun ScreenRoot() {
            ScreenBScreen()
        }

        override fun buildRouteWithArgs(): String {
            return buildRouteWithArgs(route(), screenDescription.args, listOf(name))
        }
    }
}

enum class MainScreenDescription(
    protected val route: String,
    val args: List<NamedNavArgument> = listOf()
) : ScreenDescription {
    // Screen a
    SCREEN_A("screenA"),
    // Screen b
    SCREEN_B(
        "screenB",
        listOf(
            navArgument("name") {
                type = androidx.navigation.NavType.Companion.StringType
            }
        )
    );

    override fun route() = route

    override fun argsNames() = args

    override fun buildRouteWithPlaceholders(): String {
        return if (argsNames().isEmpty()) {
            route()
        } else {
            buildRouteWithArgPlaceholders(route(), argsNames())
        }
    }
}