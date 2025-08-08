package com.devspace.recyclerview

import androidx.annotation.DrawableRes

data class Contact(
    val name: String,
    val phone: String,
    @DrawableRes val image: Int,
)
