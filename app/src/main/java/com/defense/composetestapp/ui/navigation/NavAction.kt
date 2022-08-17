package com.defense.composetestapp.ui.navigation

sealed class NavAction {

    data class Navigate(val route: String): NavAction()

    object PopBackStack : NavAction()

    object NavigateUp : NavAction()
}