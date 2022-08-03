package com.defense.composetestapp.di.navigation

interface ScreenRouter <T : NavScreen> {
    fun navigate(screen: T)
}