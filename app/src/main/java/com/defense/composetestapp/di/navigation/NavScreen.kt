package com.defense.composetestapp.di.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import java.lang.StringBuilder


abstract class NavScreen {
    private val screenKey = this.javaClass.name
    
    abstract val screenDescription: ScreenDescription

    @Composable
    abstract fun ScreenRoot()

    protected fun route(): String {
        return screenDescription.route()
    }

    open fun buildRouteWithArgs(): String {
        return route()
    }
}

fun buildRouteWithArgPlaceholders(route: String, args: List<NamedNavArgument>): String {
    val result = StringBuilder(route)

    result.append("?")

    args.forEach { arg ->
        val argName = arg.name
        result.append("$argName={$argName}")
        result.append("&")
    }

    result.deleteCharAt(result.length - 1)

    return result.toString()
}

fun buildRouteWithArgs(route: String, argsNames: List<NamedNavArgument>, args: List<Any?>): String {
    if (args.isEmpty()) {
        return route
    }

    val result = StringBuilder(route)

    result.append("?")

    args.forEachIndexed { index, argValue  ->
        if (argValue != null) {
            val argName = argsNames[index].name
            result.append("$argName=$argValue")
            result.append("&")
        }
    }

    result.deleteCharAt(result.length - 1)

    return result.toString()
}

interface ScreenDescription {

    fun route(): String

    fun argsNames(): List<NamedNavArgument>

    fun buildRouteWithPlaceholders(): String
}