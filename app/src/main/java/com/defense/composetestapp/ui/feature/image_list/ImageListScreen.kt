package com.defense.composetestapp.ui.feature.image_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.defense.composetestapp.R
import com.defense.composetestapp.ui.base.BaseScreen
import com.defense.composetestapp.ui.custom.AnimatedFullScreenProgress

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun ImageListScreen(
    vm: ImageListViewModel = hiltViewModel(),
    navController: NavController
) {
    BaseScreen(
        vm = vm,
        handleEvent = {},
        navController = navController
    ) { state, postAction ->
        Scaffold {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                val lazyListState = rememberLazyListState()

                LaunchedEffect(key1 = lazyListState.firstVisibleItemScrollOffset) {
                    postAction(
                        ListScrolledAction(
                            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index,
                            lazyListState.layoutInfo.totalItemsCount
                        )
                    )
                }

                LazyColumn(
                    state = lazyListState,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    itemsIndexed(state.items, { _, item -> item.id }) { _, item ->
                        when (item) {
                            is ImageListViewState.Item.Image -> ImageItem(item)
                            is ImageListViewState.Item.Loader -> LoaderItem()
                        }
                    }
                }
                AnimatedFullScreenProgress(state.isLoadingVisible)
            }
        }
    }
}

@Composable
private fun ImageItem(state: ImageListViewState.Item.Image) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = colorResource(id = R.color.image_item_bg)
    ) {
        Box {
            AsyncImage(
                model = state.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            
            Text(
                text = state.counter,
                modifier = Modifier.align(Alignment.Center)
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun LoaderItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}