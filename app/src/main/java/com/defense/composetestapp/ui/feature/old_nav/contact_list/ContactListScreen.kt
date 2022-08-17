package com.defense.composetestapp.ui.feature.old_nav.contact_list

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.defense.composetestapp.R
import com.defense.composetestapp.ui.base.BaseView
import com.defense.composetestapp.ui.custom.AnimatedFullScreenProgress
import com.defense.composetestapp.ui.custom.DefaultTopBar
import com.defense.composetestapp.ui.feature.old_nav.contact_detail.ContactDetailArgs
import com.defense.composetestapp.ui.feature.main.NavigationScreen
import com.defense.composetestapp.ui.theme.Shapes
import com.defense.composetestapp.ui.theme.Typography

class ContactListScreen : BaseView<
        ContactListViewModel,
        ContactListViewState,
        ContactListViewEvent,
        ContactListViewAction
>() {

    @Composable
    override fun getViewModelInstance(): ContactListViewModel = hiltViewModel()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    override fun Content(state: ContactListViewState, navController: NavController) {
        Scaffold(
            topBar = {
                 DefaultTopBar(
                     title = stringResource(id = state.titleResId),
                     navigationIconClicked = { navController.popBackStack() }
                 )
            },
            content = {
                ContactListArea(state.contactListState)
            }
        )
    }

    override fun handleEvent(event: ContactListViewEvent) {
        when (event) {
            is OpenContactDetailEvent -> navController.navigate(
                NavigationScreen.CONTACT_DETAIL.buildRoute(ContactDetailArgs(event.id))
            )
        }
    }

    @Composable
    private fun ContactListArea(contactListState: ContactListViewState.ContactListState) {
        Column {

            Button(onClick = { navController.navigate(NavigationScreen.CHART.buildRoute()) }) {
                Text(text = "Chart")
            }

            MyCheckBox()

            HelloContent()

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                when (contactListState) {
                    is ContactListViewState.ContactListState.Data -> {
                        ContentList(contactListState.contactListState)
                    }
                    is ContactListViewState.ContactListState.Error -> {
                        Text(text = stringResource(id = contactListState.errorTextResId))
                    }
                    else -> {}
                }

                AnimatedFullScreenProgress(contactListState == ContactListViewState.ContactListState.Loading)
            }
        }
    }

    @Composable
    fun MyCheckBox() {
        var isChecked by remember { mutableStateOf(false) }
        Checkbox(checked = isChecked, onCheckedChange = { isChecked = !isChecked })
    }

    @Composable
    fun HelloContent() {
        var content by remember { mutableStateOf("") }
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Hello!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Name") }
            )
        }
    }

    @Composable
    private fun ContentList(contactList: List<ContactListViewState.Contact>) {
        LazyColumn {
            items(
                items = contactList,
                key = { it.id }
            ) {
                ContactItem(it)
            }
        }
    }

    @Composable
    private fun ContactItem(item: ContactListViewState.Contact) {
        Surface(
            shape = Shapes.medium,
            elevation = dimensionResource(id = R.dimen.contact_list_item_elevation),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.default_margin),
                    vertical = dimensionResource(id = R.dimen.default_margin_half)
                )
                .clickable {
                    postAction(ContactClickedAction(item))
                }
        ) {
            Row {
                AsyncImage(
                    item.imageUrl,
                    contentDescription = "",
                    placeholder = painterResource(R.drawable.ic_contact),
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.default_margin_half),
                            top = dimensionResource(id = R.dimen.default_margin_half),
                            bottom = dimensionResource(id = R.dimen.default_margin_half),
                        )
                        .size(55.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.default_margin)))

                Column(
                    modifier = Modifier
                        .padding(
                            end = dimensionResource(id = R.dimen.default_margin),
                            top = dimensionResource(id = R.dimen.default_margin),
                            bottom = dimensionResource(id = R.dimen.default_margin),
                        )
                ) {
                    Text(
                        text = item.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = Typography.body1,
                    )
                    AnimatedVisibility(visible = item.isEmailVisible) {
                        Text(
                            text = item.email,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = Typography.body2,
                        )
                    }
                }
            }
        }
    }

    @Composable
    @Preview
    fun Preview() {
        DefaultPreview()
    }

//    @Preview
//    @Composable
//    private fun ContactItemPreview() {
//        ContactItem(
//            ContactListViewState.Contact(
//                "",
//                "maksdjh iasjdioasj doasj dasjd klasjdkash kdjaskjd askj di",
//                "someemail@asd.asd",
//                true,
//                null
//            ),
//            viewModel(),
//            rememberNavController()
//        )
//    }
}