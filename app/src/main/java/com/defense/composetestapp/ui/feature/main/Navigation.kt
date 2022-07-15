package com.defense.composetestapp.ui.feature.main

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.defense.composetestapp.ui.feature.chart.ChartScreen
import com.defense.composetestapp.ui.feature.contact_detail.ContactDetailArgs
import com.defense.composetestapp.ui.feature.contact_detail.ContactDetailScreen
import com.defense.composetestapp.ui.feature.contact_list.ContactListScreen

/**
 * First enum is first destination.
 */
enum class NavigationScreen {

    CONTACT_LIST {
        @Composable
        override fun Screen(navController: NavController, arguments: Bundle?) {
            ContactListScreen().let {
                it.Screen(navController)
            }
        }
    },

    CONTACT_DETAIL {
        @Composable
        override fun Screen(navController: NavController, arguments: Bundle?) {
            ContactDetailScreen().Screen(navController)
        }

        override fun argNames(): List<NamedNavArgument> {
            return listOf(
                navArgument("contactId") { type = NavType.StringType }
            )
        }

        override fun buildRoute(args: ScreenArgs?): String {
            require(args is ContactDetailArgs)
            return templateRoute(listOf(args.contactId))
        }
    },

    CHART {
        @Composable
        override fun Screen(navController: NavController, arguments: Bundle?) {
            ChartScreen().Screen(navController = navController)
        }
    };

    @Composable
    abstract fun Screen(navController: NavController, arguments: Bundle?)

    open fun argNames(): List<NamedNavArgument> = listOf()

    fun templateRoute(args: List<Any> = listOf()): String {
        val route = StringBuilder(this.name)

        if (argNames().isNotEmpty()) {
            route.append("?")

            argNames().forEachIndexed { index, it ->
                val value = args.getOrNull(index) ?: "{${it.name}}"
                route.append(it.name + "=$value&")
            }

            route.deleteCharAt(route.length - 1)
        }

        return route.toString()
    }

    open fun buildRoute(args: ScreenArgs? = null): String {
        return templateRoute()
    }

}

interface ScreenArgs