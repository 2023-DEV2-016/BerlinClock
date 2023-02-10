package com.berlin.uhr.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class BerlinClockUseCaseTest {

    private lateinit var useCase: BerlinClockUseCase

    @Before
    fun setUp() {
        useCase = BerlinClockUseCase(BerlinClock())
    }


    @Test
    fun `test convert to berlin clock`() {
        val berlinClockUiState = useCase.convert()

        Assert.assertNotNull(berlinClockUiState)
        Assert.assertNotNull(berlinClockUiState.normalTime)
    }
}