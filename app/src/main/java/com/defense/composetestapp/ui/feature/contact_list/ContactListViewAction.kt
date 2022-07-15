package com.defense.composetestapp.ui.feature.contact_list

import com.defense.composetestapp.ui.base.ViewAction

sealed class ContactListViewAction : ViewAction

data class ContactClickedAction(val item: ContactListViewState.Contact) : ContactListViewAction()