package com.defense.composetestapp.data.usecase

import com.defense.composetestapp.data.api.ContactsService
import com.defense.composetestapp.data.entity.data.Contact
import com.defense.composetestapp.util.mapper.toData
import kotlinx.coroutines.delay
import javax.inject.Inject

class ContactsGetUseCase @Inject constructor(
    private val contactsService: ContactsService
) {

    suspend fun perform(): Result<List<Contact>> = kotlin.runCatching {
        contacts = contactsService.getContacts().results
            ?.mapNotNull { it.toData() }
            ?: throw RuntimeException("Cant get contacts")
        contacts
    }

    companion object {
        var contacts = listOf<Contact>()
            private set
    }
}