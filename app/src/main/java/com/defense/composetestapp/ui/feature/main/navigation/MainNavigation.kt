package com.defense.composetestapp.ui.feature.main.navigation

import androidx.navigation.NavController
import com.defense.composetestapp.di.navigation.Navigation
import com.defense.composetestapp.di.navigation.NavigatorHolder
import com.defense.composetestapp.di.navigation.ScreenNavigator
import com.defense.composetestapp.di.navigation.ScreenRouter


class MainScreenRouter(private val navigationHolder: MainNavigatorHolder) :
    ScreenRouter<MainScreen> {
    override fun navigate(screen: MainScreen) {
        navigationHolder.screenNavigator?.navigate(screen)
    }
}

class MainNavigation(router: MainScreenRouter) : Navigation<MainScreenRouter>(router)

class MainNavigatorHolder : NavigatorHolder<MainScreen>()

open class MainScreenNavigator(private val navController: NavController) :
    ScreenNavigator<MainScreen> {
    override fun navigate(screen: MainScreen) {
        navController.navigate(screen.buildRouteWithArgs())
    }
}