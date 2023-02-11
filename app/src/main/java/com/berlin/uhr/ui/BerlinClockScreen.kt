package com.berlin.uhr.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
        verticalArrangement = Arrangement.Center
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
    AddFourLampsMinutesRow(modifier, clockUiState.oneMinute)
}

@Composable
fun AddFourLampsMinutesRow(modifier: Modifier, list: ArrayList<Lamps>) {
    if (list.size == 0) return
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(4) {
            Column(
                modifier = modifier
                    .padding(0.dp, 0.dp, 8.dp, 0.dp)
            ) {
                Box(
                    modifier = modifier.testTag("oneMinute$it")
                        .padding(0.dp, 8.dp, 0.dp, 8.dp)
                        .size(80.dp, 50.dp)
                        .clip(RectangleShape)
                        .border(2.dp, Color.DarkGray, RectangleShape)
                        .background(
                            if (list[it] == Lamps.YELLOW)
                                Color.Yellow else Color.White
                        )
                )
            }
        }
    }
}

@Composable
fun DrawElevenLampsRow(modifier: Modifier, list: ArrayList<Lamps>) {
    if (list.size == 0) return
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(11) {
            AddElevenLamps(list[it], modifier, "fiveMinutes$it")
        }
    }
}

@Composable
fun AddElevenLamps(lamp: Lamps, modifier: Modifier, tag: String) {
    Column(
        modifier = modifier.padding(0.dp, 0.dp, 2.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = modifier.testTag(tag)
                .padding(0.dp, 8.dp, 0.dp, 8.dp)
                .size(30.dp, 50.dp)
                .clip(RectangleShape)
                .border(2.dp, Color.DarkGray, RectangleShape)
                .background(
                    when (lamp) {
                        Lamps.RED -> Color.Red
                        Lamps.YELLOW -> Color.Yellow
                        else -> Color.White
                    }
                )
        )
    }
}

@Composable
fun DrawHourLamps(modifier: Modifier, clockUiState: BerlinClockUiState) {
    AddFourLampsHourRow(modifier, clockUiState.fiveHours,"fiveHourLamp")
    AddFourLampsHourRow(modifier, clockUiState.oneHour, "oneHourLamp")
}

@Composable
fun AddFourLampsHourRow(modifier: Modifier, list: ArrayList<Lamps>, tag: String) {
    if (list.size == 0) return
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(4) {
            AddLamps(list[it], modifier, "$tag$it")
        }
    }

}

@Composable
fun AddLamps(lamp: Lamps, modifier: Modifier, tag: String) {
    Column(
        modifier = modifier
            .padding(0.dp, 0.dp, 8.dp, 0.dp)
    ) {
        Box(
            modifier = modifier.testTag(tag)
                .padding(0.dp, 8.dp, 0.dp, 8.dp)
                .size(80.dp, 50.dp)
                .clip(RectangleShape)
                .border(2.dp, Color.DarkGray, RectangleShape)
                .background(
                    if (lamp == Lamps.RED)
                        Color.Red else Color.White
                )
        )
    }
}

@Composable
fun DrawNormalTime(modifier: Modifier, normalTime: String?) {
    Text(
        modifier = modifier.testTag("normalTime"),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        text = normalTime ?: ""
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
                if (seconds == Lamps.RED)
                    Color.Red else Color.White
            )
    )
}
