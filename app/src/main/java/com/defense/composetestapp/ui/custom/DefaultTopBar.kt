package com.defense.composetestapp.ui.custom

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

@Composable
fun DefaultTopBar(title: String, navigationIconClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { navigationIconClicked() }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        }
    )
}