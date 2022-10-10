package com.hammad.riyadh.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Event(
    val id: Int,
    val eventType: String,
    @DrawableRes val typeImage: Int,
    val imageUrl: String,
    @StringRes val name: Int,
    val placeName: String,
    val priceFrom: Int,
    val priceTo: Int,
    val dateFrom: String,
    val dateTo: String
)
