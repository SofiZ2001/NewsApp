package com.example.data.response

import com.example.domain.model.UserModel
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("firstname") val firstName: String,
    @SerializedName("lastname") val lastName: String,
    @SerializedName("birthDate") val birthDate: String,
    @SerializedName("address") val address: AddressResponse
)

fun UserResponse.toModel(): UserModel = UserModel(firstName, lastName, birthDate, address.toModel())

fun List<UserResponse>.toListModel(): List<UserModel> = this.map { it.toModel() }
