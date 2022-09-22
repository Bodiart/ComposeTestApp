package com.defense.composetestapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

const val ARG_JSON_ENCODING = "UTF-8"

abstract class NavDestination<T : NavArgs>(val clazz: Class<T>) {

    open fun route(): String = this::class.java.name

    fun argNames(): List<NamedNavArgument> = if (clazz is NoArgs) {
        listOf()
    } else {
        listOf(navArgument(clazz.argsName) { type = NavType.StringType } )
    }

    fun routeWithPlaceholders(): String {
        if (clazz is NoArgs) {
            return route()
        }

        return route() + "?${clazz.argsName}={${clazz.argsName}}"
    }

    fun routeWithArguments(args: T): String {
        if (clazz is NoArgs) {
            return route()
        }

        val json = Gson().toJson(args, clazz)
        val serializedJson = serializeJsonForUrl(json)

        return routeWithPlaceholders().replace("{${clazz.argsName}}", serializedJson)
    }

    @Composable
    abstract fun Screen(navController: NavController)
}

interface NavArgs

/**
 * For screen with no args.
 */
interface NoArgs : NavArgs

fun serializeJsonForUrl(json: String): String =
        URLEncoder.encode(json, ARG_JSON_ENCODING)

fun deserializeJsonFromUrl(urlJson: String): String =
        URLDecoder.decode(urlJson, ARG_JSON_ENCODING)

val <T : NavArgs> Class<T>.argsName: String
    get() = name