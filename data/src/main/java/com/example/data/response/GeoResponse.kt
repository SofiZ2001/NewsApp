package com.example.data.response

import com.example.domain.model.GeoModel
import com.google.gson.annotations.SerializedName

data class GeoResponse(
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String
)

fun GeoResponse.toModel() = GeoModel(lat, lng)
