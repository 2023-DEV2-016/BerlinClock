package com.berlin.uhr.domain

import com.berlin.uhr.domain.model.BerlinClockValue
import com.berlin.uhr.domain.model.Lamps


class BerlinClock {

    fun convert(time: String): BerlinClockValue {
        val splitTime = time.split(":")
        val seconds = convertSeconds(splitTime[2].toInt())
        val hours = convert5hoursLamps(splitTime[0].toInt())

        return BerlinClockValue(seconds, hours, arrayListOf(), arrayListOf(), arrayListOf())
    }

    private fun convert5hoursLamps(hours: Int): ArrayList<Lamps> {
        val hourLamps = hours / 5
        val list = arrayListOf<Lamps>()
        for (i in 1..4) {
            list.add(
                if (i <= hourLamps)
                    Lamps.RED
                else Lamps.OFF
            )
        }
        return list
    }

    private fun convertSeconds(seconds: Int): Lamps {
        return if(seconds % 2 == 0) Lamps.RED else Lamps.OFF
    }
}