package com.berlin.uhr.ui

import com.berlin.uhr.domain.BerlinClockUseCase
import com.berlin.uhr.domain.model.BerlinClockUiState
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.assertEquals


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