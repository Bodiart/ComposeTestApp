package com.defense.composetestapp.ui.feature.contact_list

import androidx.annotation.StringRes
import com.defense.composetestapp.ui.base.ViewState

data class ContactListViewState(
    @StringRes val titleResId: Int,
    val contactListState: ContactListState,
) : ViewState {

    sealed class ContactListState {
        data class Data(val contactListState: List<Contact>) : ContactListState()

        object Loading : ContactListState()

        data class Error(@StringRes val errorTextResId: Int) : ContactListState()
    }


    data class Contact(
        val id: String,
        val name: String,
        val email: String,
        val isEmailVisible: Boolean,
        val imageUrl: String?
    )
}