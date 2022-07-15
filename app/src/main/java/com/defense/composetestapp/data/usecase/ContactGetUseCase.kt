package com.defense.composetestapp.data.usecase

import com.defense.composetestapp.data.entity.data.Contact
import javax.inject.Inject

class ContactGetUseCase @Inject constructor() {

    fun perform(id: String): Contact? {
        return ContactsGetUseCase.contacts.firstOrNull { it.email == id }
    }
}