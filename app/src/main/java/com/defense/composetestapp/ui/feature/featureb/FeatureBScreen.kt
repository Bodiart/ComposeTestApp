package com.defense.composetestapp.ui.feature.featureb

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.defense.composetestapp.ui.base.BaseScreen

@Composable
fun FeatureBScreen(
    navController: NavController,
    vm: FeatureBViewModel = hiltViewModel()
) {
    BaseScreen(
        vm = vm,
        handleEvent = {},
        navController = navController
    ) { state, postAction ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = state.text,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}