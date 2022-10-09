package com.hammad.riyadh.models

import androidx.annotation.DrawableRes

data class Event(
    val id: Int,
    val eventType: String,
    @DrawableRes val typeImage: Int,
    val imageUrl: String,
    val name: String,
    val placeName: String,
    val priceFrom: Int,
    val priceTo: Int,
    val dateFrom: String,
    val dateTo: String
)
