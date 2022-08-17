package com.defense.composetestapp.ui.feature.old_nav.contact_list

import com.defense.composetestapp.ui.base.ViewEvent

sealed class ContactListViewEvent : ViewEvent

data class OpenContactDetailEvent(val id: String) : ContactListViewEvent()