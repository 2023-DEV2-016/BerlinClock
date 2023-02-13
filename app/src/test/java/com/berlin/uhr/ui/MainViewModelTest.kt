package com.berlin.uhr.ui

import com.berlin.uhr.domain.BerlinClockUseCase
import com.berlin.uhr.domain.model.BerlinClockUiState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val useCase = mockk<BerlinClockUseCase>()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `start converting time to berlin clock`() = runTest {
        val uiState = BerlinClockUiState(normalTime = "23:43:11")
        every { useCase.initConversion() } returns flowOf(uiState)
        viewModel = MainViewModel(useCase)

        val job = launch {
            viewModel.berlinClockState.collect()
        }
        runCurrent()

        assertEquals(uiState, viewModel.berlinClockState.value)
        job.cancel()
    }
}
