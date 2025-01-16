package com.example.domain.model

data class UserModel(
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val address: AddressModel
)
