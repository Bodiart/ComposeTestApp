package com.defense.composetestapp.di.module

import com.defense.composetestapp.ui.feature.main.navigation.MainNavigation
import com.defense.composetestapp.ui.feature.main.navigation.MainNavigatorHolder
import com.defense.composetestapp.ui.feature.main.navigation.MainScreenRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

const val DI_NAME_NAVIGATION_MAIN = "DI_NAME_NAVIGATION_MAIN"

@InstallIn(SingletonComponent::class)
@Module
class NavigationModule {

    @Singleton
    @Provides
    @Named(DI_NAME_NAVIGATION_MAIN)
    fun provideNavigatorHolder(): MainNavigatorHolder = MainNavigatorHolder()

    @Singleton
    @Provides
    @Named(DI_NAME_NAVIGATION_MAIN)
    fun provideMainNavigation(
        @Named(DI_NAME_NAVIGATION_MAIN) mainNavigatorHolder: MainNavigatorHolder
    ): MainNavigation = MainNavigation(MainScreenRouter(mainNavigatorHolder))

    @Singleton
    @Provides
    @Named(DI_NAME_NAVIGATION_MAIN)
    fun provideMainRouter(@Named(DI_NAME_NAVIGATION_MAIN) navigation: MainNavigation): MainScreenRouter =
        navigation.router
}