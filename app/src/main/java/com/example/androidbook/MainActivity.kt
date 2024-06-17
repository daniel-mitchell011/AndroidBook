package com.example.androidbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidbook.ui.theme.AndroidBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidBookTheme {
                DiceRollerApp()
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage()
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)) {
    var result by remember { mutableStateOf(0) }
    val paintings =    listOf(R.drawable.river_landscape_with_villages_and_travelers__verso__1997_85_1_b,
                              R.drawable.christ_at_the_sea_of_galilee_1952_5_27,
                              R.drawable._952_343___under_the_wave_off_kanagawa__kanagawa_oki_nami___,
                              R.drawable._938_541___poem_by_sarumaru_dayu__from_the_series__one___)
    val titles = listOf(R.string.title1, R.string.title2, R.string.title3, R.string.title4)
    val authors =      listOf(R.string.author1, R.string.author2, R.string.author3, R.string.author4)
    val numCases = 4
    val imageResource = paintings[result]

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = "Paintings"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Surface(modifier = Modifier.fillMaxWidth(0.8f).padding(4.dp), color = Color(0xdddddddd)) {
            Column() {
                Text(text = "${stringResource(titles[result])}", textAlign = TextAlign.Center, fontSize = 20.sp)
                Text(text = "${stringResource(authors[result])}", textAlign = TextAlign.Center, fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier) {
            OutlinedButton(modifier = Modifier.width(130.dp), onClick = { if(result <= 0) {result = numCases - 1} else {result = (result - 1) % numCases} }, enabled = true) {
                Text(text = "Previous", color = Color(0xff000000))
            }
            OutlinedButton(modifier = Modifier.width(130.dp), onClick = {result = (result + 1) % numCases}) {
                Text("Next", color = Color(0xff000000))
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier.width(270.dp), onClick = { result = (0..(numCases - 1)).random() }) {
            Text(stringResource(R.string.random), fontSize = 20.sp)
        }
    }
}