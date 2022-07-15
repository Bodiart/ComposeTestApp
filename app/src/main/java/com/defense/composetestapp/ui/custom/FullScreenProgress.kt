package com.defense.composetestapp.ui.custom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FullScreenProgress() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun AnimatedFullScreenProgress(isVisible: Boolean) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(tween(300)),
        exit = fadeOut(tween(300))
    ) {
        FullScreenProgress()
    }
}