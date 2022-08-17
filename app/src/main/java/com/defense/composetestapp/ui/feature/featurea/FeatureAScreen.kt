package com.defense.composetestapp.ui.feature.featurea

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.defense.composetestapp.R
import com.defense.composetestapp.ui.base.BaseScreen

@Composable
fun FeatureAScreen(
    navController: NavController,
    vm: FeatureAViewModel = hiltViewModel()
) {
    BaseScreen(
        vm = vm,
        handleEvent = {},
        navController = navController
    ) { state, postAction ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white))
        ) {
            Button(onClick = { postAction(Button1ClickedAction) }) {
                Text(text = "button 1")
            }
            Button(onClick = { postAction(Button2ClickedAction) }) {
                Text(text = "button 2")
            }
        }
    }
}