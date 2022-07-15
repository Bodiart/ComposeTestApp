package com.defense.composetestapp.ui.feature.contact_detail

import com.defense.composetestapp.ui.base.ViewAction

sealed class ContactDetailViewAction : ViewAction

data class EmailChangedAction(val text: String) : ContactDetailViewAction()

data class NameChangedAction(val text: String) : ContactDetailViewAction()