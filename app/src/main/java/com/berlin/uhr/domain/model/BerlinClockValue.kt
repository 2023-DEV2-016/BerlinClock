package com.berlin.uhr.domain.model

data class BerlinClockValue(
    val seconds: Lamps,
    val fiveHours: ArrayList<Lamps>,
    val oneHour: ArrayList<Lamps>,
    val fiveMinutes: ArrayList<Lamps>,
    val oneMinute: ArrayList<Lamps>
)
