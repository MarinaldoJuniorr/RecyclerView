package com.devspace.recyclerview

import androidx.annotation.DrawableRes

data class Contact(
    @DrawableRes val icon: Int,
    val name: String,
    val phone: String)

