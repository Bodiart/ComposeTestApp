package com.defense.composetestapp.ui.base

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<VS : ViewState, VE : ViewEvent, VA : ViewAction>(
) : ViewModel() {

    private val initialViewState: VS by lazy { createInitialState() }

    protected var lastViewState: VS = initialViewState

    protected val mutableViewStateFlow = MutableStateFlow(initialViewState)
    val viewStateFlow = mutableViewStateFlow.asStateFlow()

    protected val eventChannel = Channel<VE?>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()

    protected val actionChannel = Channel<VA>(Channel.BUFFERED)


    init {
        viewModelScope.launch {
            actionChannel.consumeEach { action ->
                handleAction(action)
            }
        }
    }

    protected abstract fun createInitialState(): VS

    protected abstract fun handleAction(action: VA)

    protected fun updateState(update: VS.() -> VS) {
        mutableViewStateFlow.value.let {
            mutableViewStateFlow.value = update(it)
            lastViewState = mutableViewStateFlow.value
        }
    }

    protected fun postEvent(event: VE) = viewModelScope.launch {
        eventChannel.send(null)
        eventChannel.send(event)
    }

    fun applyAction(action: VA) = viewModelScope.launch {
        actionChannel.send(action)
    }

    open fun onViewActive() {
    }
}