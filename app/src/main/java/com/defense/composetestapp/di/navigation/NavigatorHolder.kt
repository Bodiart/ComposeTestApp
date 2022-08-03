package com.defense.composetestapp.di.navigation

abstract class NavigatorHolder <T : NavScreen> {
    var screenNavigator: ScreenNavigator<T>? = null
        private set

    fun setNavigator(screenNavigator: ScreenNavigator<T>) {
        this.screenNavigator = screenNavigator
    }

    fun removeNavigator() {
        screenNavigator = null
    }
}