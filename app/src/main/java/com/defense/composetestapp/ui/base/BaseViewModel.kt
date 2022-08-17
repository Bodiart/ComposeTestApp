package com.defense.composetestapp.ui.base

import androidx.annotation.MainThread
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.defense.composetestapp.ui.navigation.NavAction
import com.defense.composetestapp.ui.navigation.NavArgs
import com.defense.composetestapp.ui.navigation.argsName
import com.defense.composetestapp.ui.navigation.deserializeJsonFromUrl
import com.google.gson.Gson
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

abstract class BaseViewModel<VS : ViewState, VE : ViewEvent, VA : ViewAction>(
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    protected val mutableViewStateFlow = MutableStateFlow<VS?>(null) // StateFlow - drop olds and receive only new state
    val viewStateFlow = mutableViewStateFlow.asStateFlow()

    protected val eventChannel = Channel<VE?>(Channel.BUFFERED) // Channel - all events should be posted, old and new ones, one subscriber
    val eventFlow = eventChannel.receiveAsFlow()

    protected val actionChannel = Channel<VA>(Channel.BUFFERED) // Channel - all events should be posted, old and new ones, one subscriber

    protected val routeChannel = Channel<NavAction?>(Channel.BUFFERED)
    val routeFlow = routeChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            actionChannel.consumeEach { action ->
                handleAction(action)
            }
        }
    }

    protected abstract fun handleAction(action: VA)

    protected fun updateState(update: VS.() -> VS) {
        mutableViewStateFlow.value?.let {
            mutableViewStateFlow.value = update(it)
        }
    }

    protected fun postEvent(event: VE) = viewModelScope.launch {
        eventChannel.send(null)
        eventChannel.send(event)
    }

    protected fun navigate(navAction: NavAction) = viewModelScope.launch {
        routeChannel.send(null)
        routeChannel.send(navAction)
    }

    protected fun <T : NavArgs> arguments(clazz: Class<T>): T {
        val urlJson = savedStateHandle.get<String>(clazz.argsName)
                ?: throw IllegalStateException("ViewModel $this has null arguments")
        val deserializedJson = deserializeJsonFromUrl(urlJson)
        return Gson().fromJson(deserializedJson, clazz)
    }

    @MainThread
    protected inline fun <reified T : NavArgs> navArgs() = arguments(T::class.java)


    fun applyAction(action: VA) = viewModelScope.launch {
        actionChannel.send(action)
    }

    open fun onViewActive() {
    }
}