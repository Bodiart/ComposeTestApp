package com.defense.composetestapp.data.entity.data

data class Contact(
    val email: String,
    val name: String,
    val picture: ContactPicture?
)

data class ContactPicture(
    val mediumUrl: String?,
    val largeUrl: String?,
    val thumbnail: String?
)