package com.berlin.uhr.domain

import androidx.annotation.VisibleForTesting
import com.berlin.uhr.domain.model.BerlinClockUiState
import com.berlin.uhr.domain.model.BerlinClockValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class BerlinClockUseCase @Inject constructor(
    private val berlinClock: BerlinClock,
) {

    fun initConversion(): Flow<BerlinClockUiState> {
        return flow {
            while (true) {
                emit(convert())
                delay(1000L)
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun convert(): BerlinClockUiState {
        val time = formatTime(Calendar.getInstance().timeInMillis)
        return toBerlinClockUiState(berlinClock.convert(time), time)
    }

    private fun toBerlinClockUiState(berlinClockValue: BerlinClockValue, time: String) =
        BerlinClockUiState(
            seconds = berlinClockValue.seconds,
            fiveHours = berlinClockValue.fiveHours,
            oneHour = berlinClockValue.oneHour,
            fiveMinutes = berlinClockValue.fiveMinutes,
            oneMinute = berlinClockValue.oneMinute,
            normalTime = time,
        )

    private fun formatTime(time: Long): String =
        SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
            .format(Date(time))
}
