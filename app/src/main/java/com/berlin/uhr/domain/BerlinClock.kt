package com.berlin.uhr.domain

import com.berlin.uhr.domain.model.BerlinClockValue
import com.berlin.uhr.domain.model.Lamps
import javax.inject.Inject


class BerlinClock @Inject constructor() {

    fun convert(time: String): BerlinClockValue {
        val splitTime = time.split(":")
        val seconds = convertSeconds(splitTime[2].toInt())
        val fiveHours = convert5hoursLamps(splitTime[0].toInt())
        val oneHour = convert1hoursLamps(splitTime[0].toInt())
        val fiveMinutes = convert5minutesLamps(splitTime[1].toInt())
        val oneMinute = convert1minutesLamps(splitTime[1].toInt())

        return BerlinClockValue(seconds, fiveHours, oneHour, fiveMinutes, oneMinute)
    }

    private fun convert1minutesLamps(minutes: Int): ArrayList<Lamps> {
        val oneMinuteLamps = minutes % 5
        val list = arrayListOf<Lamps>()
        for (i in 1..4) {
            list.add(
                if (i <= oneMinuteLamps)
                    Lamps.YELLOW
                else Lamps.OFF
            )
        }
        return list
    }

    private fun convert5minutesLamps(minutes: Int): ArrayList<Lamps> {
        val fiveMinutesLamps = minutes / 5
        val list = arrayListOf<Lamps>()
        for (i in 1..11) {
            list.add(
                if (i <= fiveMinutesLamps) {
                    if(i % 3 == 0)
                        Lamps.RED
                    else Lamps.YELLOW
                }
                else Lamps.OFF
            )
        }
        return list
    }

    private fun convert1hoursLamps(hours: Int): ArrayList<Lamps> {
        return convertHours(hours % 5)
    }

    private fun convert5hoursLamps(hours: Int): ArrayList<Lamps> {
        return convertHours(hours / 5)
    }

    private fun convertHours(hourLamps: Int): ArrayList<Lamps> {
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