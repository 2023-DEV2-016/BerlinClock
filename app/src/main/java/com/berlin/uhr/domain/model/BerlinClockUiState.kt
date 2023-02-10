package com.berlin.uhr.domain.model


data class BerlinClockUiState(
    val seconds: Lamps,
    val fiveHours: ArrayList<Lamps>,
    val oneHour: ArrayList<Lamps>,
    val fiveMinutes: ArrayList<Lamps>,
    val oneMinute: ArrayList<Lamps>,
    val normalTime: String
)
