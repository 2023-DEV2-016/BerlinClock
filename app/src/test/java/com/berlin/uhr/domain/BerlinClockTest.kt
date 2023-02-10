package com.berlin.uhr.domain

import com.berlin.uhr.domain.model.Lamps
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class BerlinClockTest {

    private lateinit var berlinClock: BerlinClock

    @Before
    fun setUp() {
        berlinClock = BerlinClock()
    }

    @Test
    fun `test seconds lamp for even number`() {
        val time = "15:23:12"

        val converted = berlinClock.convert(time)

        Assert.assertEquals(Lamps.RED, converted)
    }

    @Test
    fun `test seconds lamp for odd number`() {
        val time = "15:23:15"

        val converted = berlinClock.convert(time)

        Assert.assertEquals(Lamps.OFF, converted)
    }

}