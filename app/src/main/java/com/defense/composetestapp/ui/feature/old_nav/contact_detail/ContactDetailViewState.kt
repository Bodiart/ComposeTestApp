package com.defense.composetestapp.ui.feature.old_nav.contact_detail

import com.defense.composetestapp.ui.base.ViewState

data class ContactDetailViewState(
    val title: String,
    val imageUrl: String?,
    val email: String?,
    val name: String
) : ViewState