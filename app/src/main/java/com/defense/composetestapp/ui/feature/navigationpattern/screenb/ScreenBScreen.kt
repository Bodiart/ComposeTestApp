package com.defense.composetestapp.ui.feature.navigationpattern.screenb

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.defense.composetestapp.ui.base.BaseScreen

@Composable
fun ScreenBScreen(vm: ScreenBViewModel = hiltViewModel()) {
    BaseScreen(vm = vm, handleEvent = {}) { state, postAction ->
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = state.name)
        }
    }
}