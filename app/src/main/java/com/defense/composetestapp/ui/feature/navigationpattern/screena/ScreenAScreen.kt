package com.defense.composetestapp.ui.feature.navigationpattern.screena

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.defense.composetestapp.ui.base.BaseScreen

@Composable
fun ScreenAScreen(vm: ScreenAViewModel = hiltViewModel()) {
    BaseScreen(vm = vm, handleEvent = ::handleEvent) { state, postAction ->
        Box(
            modifier = Modifier.fillMaxSize()
        )
        {
            Row {
                Button(onClick = { postAction.invoke(Button1ClickedAction) }) {
                    Text(text = state.button1)
                }
                Button(onClick = { postAction.invoke(Button2ClickedAction) }) {
                    Text(text = state.button2)
                }
            }
        }
    }
}

private fun handleEvent(event: ScreenAViewEvent) {
}