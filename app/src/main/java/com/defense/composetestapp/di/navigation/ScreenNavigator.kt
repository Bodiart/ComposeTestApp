package com.defense.composetestapp.di.navigation

interface ScreenNavigator<T : NavScreen> {
    fun navigate(screen: T)
}