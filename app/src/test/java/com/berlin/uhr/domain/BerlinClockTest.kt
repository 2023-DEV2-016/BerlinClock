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

        Assert.assertEquals(Lamps.RED, converted.seconds)
    }

    @Test
    fun `test seconds lamp for odd number`() {
        val time = "15:23:15"

        val converted = berlinClock.convert(time)

        Assert.assertEquals(Lamps.OFF, converted.seconds)
    }

    @Test
    fun `test 5 hour lamps with full red`() {
        val time = "21:23:12"
        val expected = arrayListOf(Lamps.RED,Lamps.RED,Lamps.RED,Lamps.RED)

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.fiveHours)
    }

    @Test
    fun `test 5 hour lamps with both red and off`() {
        val time = "15:23:12"
        val expected = arrayListOf(Lamps.RED,Lamps.RED,Lamps.RED,Lamps.OFF)

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.fiveHours)
    }

    @Test
    fun `test 5 hour lamps with full off`() {
        val time = "00:23:12"
        val expected = arrayListOf(Lamps.OFF,Lamps.OFF,Lamps.OFF,Lamps.OFF)

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.fiveHours)
    }

}