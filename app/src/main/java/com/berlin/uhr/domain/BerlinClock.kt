package com.berlin.uhr.domain

import com.berlin.uhr.domain.model.Lamps


class BerlinClock {

    fun convert(time: String): Lamps {
        val splitTime = time.split(":")
        val seconds = splitTime[2].toInt()
        return if(seconds % 2 == 0) Lamps.RED else Lamps.OFF
    }
}