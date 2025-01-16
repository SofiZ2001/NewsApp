package com.example.newsapp.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostData(
    val title: String,
    val content: String
) : Parcelable
