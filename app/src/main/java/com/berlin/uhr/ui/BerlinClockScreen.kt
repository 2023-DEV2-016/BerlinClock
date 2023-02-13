package com.berlin.uhr.ui

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.berlin.uhr.domain.model.BerlinClockUiState
import com.berlin.uhr.domain.model.Lamps

@Composable
fun BerlinClockScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    val clockUiState by viewModel.berlinClockState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        DrawNormalTime(modifier, clockUiState.normalTime)
        DrawSecondsLamp(modifier, clockUiState.seconds)
        DrawHourLamps(modifier, clockUiState)
        DrawMinuteLamps(modifier, clockUiState)
    }
}

@Composable
fun DrawMinuteLamps(modifier: Modifier, clockUiState: BerlinClockUiState) {
    DrawElevenLampsRow(modifier, clockUiState.fiveMinutes)
    DrawFourLampsRow(modifier, clockUiState.oneMinute, "oneMinute")
}

@Composable
fun DrawElevenLampsRow(modifier: Modifier, list: ArrayList<Lamps>) {
    if (list.size == 0) return
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(11) {
            DrawElevenLamps(list[it], modifier, "fiveMinutes$it")
        }
    }
}

@Composable
fun DrawElevenLamps(lamp: Lamps, modifier: Modifier, tag: String) {
    Column(
        modifier = modifier.padding(0.dp, 0.dp, 2.dp, 0.dp),
    ) {
        DrawRectangle(modifier, tag, lamp, 30)
    }
}

@Composable
fun DrawHourLamps(modifier: Modifier, clockUiState: BerlinClockUiState) {
    DrawFourLampsRow(modifier, clockUiState.fiveHours, "fiveHourLamp")
    DrawFourLampsRow(modifier, clockUiState.oneHour, "oneHourLamp")
}

@Composable
fun DrawFourLampsRow(modifier: Modifier, list: ArrayList<Lamps>, tag: String) {
    if (list.size == 0) return
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(4) {
            DrawLamps(list[it], modifier, "$tag$it")
        }
    }
}

@Composable
fun DrawLamps(lamp: Lamps, modifier: Modifier, tag: String) {
    Column(
        modifier = modifier
            .padding(0.dp, 0.dp, 8.dp, 0.dp),
    ) {
        DrawRectangle(modifier, tag, lamp, 80)
    }
}

@Composable
fun DrawNormalTime(modifier: Modifier, normalTime: String?) {
    Text(
        modifier = modifier.testTag("normalTime"),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        text = normalTime ?: "",
    )
}

@Composable
fun DrawSecondsLamp(modifier: Modifier, seconds: Lamps) {
    Box(
        modifier = modifier.testTag("secondsLamp")
            .padding(0.dp, 8.dp, 0.dp, 8.dp)
            .size(50.dp)
            .clip(CircleShape)
            .border(2.dp, Color.DarkGray, CircleShape)
            .background(
                Color(parseColor(seconds.color)),
            ),
    )
}

@Composable
private fun DrawRectangle(
    modifier: Modifier,
    tag: String,
    lamp: Lamps,
    width: Int,
    height: Int = 50,
) {
    Box(
        modifier = modifier
            .testTag(tag)
            .padding(0.dp, 8.dp, 0.dp, 8.dp)
            .size(width.dp, height.dp)
            .clip(RectangleShape)
            .border(2.dp, Color.DarkGray, RectangleShape)
            .background(
                Color(parseColor(lamp.color)),
            ),
    )
}
