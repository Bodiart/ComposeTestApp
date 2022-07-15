package com.defense.composetestapp.ui.feature.contact_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.defense.composetestapp.R
import com.defense.composetestapp.ui.base.BaseView
import com.defense.composetestapp.ui.custom.DefaultTopBar

class ContactDetailScreen : BaseView<
        ContactDetailViewModel,
        ContactDetailViewState,
        ContactDetailViewEvent,
        ContactDetailViewAction
        >() {

    @Composable
    override fun getViewModelInstance(): ContactDetailViewModel = hiltViewModel()

    @Composable
    override fun Content(
        state: ContactDetailViewState
    ) {

        Scaffold(
            topBar = {
                DefaultTopBar(
                    title = state.title,
                    navigationIconClicked = { navController.popBackStack() }
                )
            },
            content = {
                ScreenContent(state, viewModel)
            }
        )
    }

    override fun handleEvent(event: ContactDetailViewEvent) {
    }

    @Preview
    @Composable
    private fun Preview() {
        DefaultPreview()
    }

    @Composable
    private fun ScreenContent(state: ContactDetailViewState, viewModel: ContactDetailViewModel) {
        Column {
            AsyncImage(
                model = state.imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_contact)
            )
            TextField(
                value = state.email ?: "",
                onValueChange = {
                    postAction(EmailChangedAction(it))
                },
                label = { Text(state.email ?: "") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.default_margin),
                        top = dimensionResource(id = R.dimen.default_margin),
                        end = dimensionResource(id = R.dimen.default_margin),
                    )
            )
            TextField(
                value = state.name,
                onValueChange = {
                    postAction(NameChangedAction(it))
                },
                label = { Text(state.name) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.default_margin))
            )
        }
    }
}