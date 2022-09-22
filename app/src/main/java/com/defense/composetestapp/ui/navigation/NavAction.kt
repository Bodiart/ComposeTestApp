package com.defense.composetestapp.ui.navigation

import com.google.gson.Gson

sealed class NavAction {

    data class Navigate(val route: NavRoute<*>): NavAction()

    object PopBackStack : NavAction()

    object NavigateUp : NavAction()
}

data class NavRoute <T : NavArgs> (
    val navDestination: NavDestination<T>,
    val args: T
) {

    fun routeWithArguments(): String {
        if (navDestination.clazz is NoArgs) {
            return navDestination.route()
        }

        val json = Gson().toJson(args, navDestination.clazz)
        val serializedJson = serializeJsonForUrl(json)

        return routeWithPlaceholders().replace("{${navDestination.clazz.argsName}}", serializedJson)
    }

    fun routeWithPlaceholders(): String {
        if (navDestination.clazz is NoArgs) {
            return navDestination.route()
        }

        return navDestination.route() + "?${navDestination.clazz.argsName}={${navDestination.clazz.argsName}}"
    }
}