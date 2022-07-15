package com.defense.composetestapp.util.extensions


inline fun <T> Result<T>.process(
    crossinline successFunction: (T) -> Unit,
    crossinline failureFunction: (Throwable) -> Unit,
    crossinline completionFunction: () -> Unit = {}
) {
    try {
        getOrThrow().let(successFunction)
    } catch (throwable: Throwable) {
        failureFunction(throwable)
    }
    completionFunction()
}