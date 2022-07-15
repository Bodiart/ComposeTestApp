package com.defense.composetestapp.util.mapper

import com.defense.composetestapp.data.entity.api.ContactApi
import com.defense.composetestapp.data.entity.data.Contact
import com.defense.composetestapp.data.entity.data.ContactPicture

fun ContactApi.toData(): Contact? {
    val name = if (this.name?.first != null || this.name?.last != null) {
        (this.name.first ?: "") + " " + (this.name.last ?: "")
    } else {
        null
    }
    return Contact(
        this.email ?: return null,
        name ?: return null,
        ContactPicture(
            this.picture?.medium,
            this.picture?.large,
            this.picture?.thumbnail
        )
    )
}