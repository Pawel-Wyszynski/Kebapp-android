package com.pz.kebapp.data.models

data class Data(
    val id: Int,
    val name: String,
    val logoUrl: String,
    val address: String,
    val coordinatesX: Double,
    val coordinatesY: Double,
    val openingYear: Int,
    val closingYear: Int,
    val status: String,
    val isKraft: Boolean,
    val isFoodTruck: Boolean,
    val network: String,
    val appLink: String,
    val websiteLink: String,
    val hasGlovo: Boolean,
    val hasPyszne: Boolean,
    val hasUberEats: Boolean,
    val phoneNumber: String,
    val likeCount: Int,
    val isOpenNow: Boolean,
    val meatTypes: List<MeatType>,
    val sauces: List<Sauce>,
    val openingHours: List<OpeningHour>,
    val comments: List<String>
)