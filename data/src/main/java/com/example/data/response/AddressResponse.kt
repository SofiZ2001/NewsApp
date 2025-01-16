package com.example.data.response

import com.example.domain.model.AddressModel
import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("geo") val geo: GeoResponse
)

fun AddressResponse.toModel() = AddressModel(geo.toModel())