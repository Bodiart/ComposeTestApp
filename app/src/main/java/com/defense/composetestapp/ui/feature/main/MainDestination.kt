package com.defense.composetestapp.ui.feature.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.defense.composetestapp.ui.feature.featurea.FeatureAScreen
import com.defense.composetestapp.ui.feature.featureb.FeatureBArgs
import com.defense.composetestapp.ui.feature.featureb.FeatureBScreen
import com.defense.composetestapp.ui.navigation.NavArgs
import com.defense.composetestapp.ui.navigation.NavDestination
import com.defense.composetestapp.ui.navigation.NoArgs

sealed class MainDestination<T : NavArgs>(clazz: Class<T>) : NavDestination<T>(clazz) {

    object FeatureA : MainDestination<NoArgs>(NoArgs::class.java) {
        @Composable
        override fun Screen(navController: NavController) {
            FeatureAScreen(navController)
        }
    }

    object FeatureB : MainDestination<FeatureBArgs>(FeatureBArgs::class.java) {
        @Composable
        override fun Screen(navController: NavController) {
            FeatureBScreen(navController)
        }
    }
}