package com.defense.composetestapp.ui.feature.contact_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.defense.composetestapp.R
import com.defense.composetestapp.data.entity.data.Contact
import com.defense.composetestapp.data.usecase.ContactsGetUseCase
import com.defense.composetestapp.ui.base.BaseViewModel
import com.defense.composetestapp.util.extensions.process
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val contactsGetUseCase: ContactsGetUseCase
) : BaseViewModel<
        ContactListViewState,
        ContactListViewEvent,
        ContactListViewAction
>() {

    private var contactList = listOf<Contact>()

    init {
        setupContactList()
    }

    override fun createInitialState() = ContactListViewState(
        titleResId = R.string.contact_list_title,
        contactListState = ContactListViewState.ContactListState.Loading
    )

    override fun handleAction(action: ContactListViewAction) {
        when (action) {
            is ContactClickedAction -> postEvent(OpenContactDetailEvent(action.item.id))
        }
    }

    private fun setupContactList() = viewModelScope.launch {
        updateState {
            copy(
                contactListState = ContactListViewState.ContactListState.Loading
            )
        }
        contactsGetUseCase.perform().process(
            {
                contactList = it
                refreshContactList()
            },
            {
                updateState {
                    copy(
                        contactListState = ContactListViewState.ContactListState.Error(
                            R.string.contact_list_get_failed
                        )
                    )
                }
            }
        )
    }

    private fun refreshContactList() {
        updateState {
            copy(
                contactListState = ContactListViewState.ContactListState.Data(
                    makeContactAdapterList(contactList)
                )
            )
        }
    }

    private fun makeContactAdapterList(contactList: List<Contact>): List<ContactListViewState.Contact> {
        return contactList.map {
            ContactListViewState.Contact(
                id = it.email,
                name = it.name,
                email = it.email,
                isEmailVisible = it.email.isNotEmpty(),
                imageUrl = it.picture?.thumbnail
                    ?: it.picture?.mediumUrl
                    ?: it.picture?.largeUrl
            )
        }
    }
}