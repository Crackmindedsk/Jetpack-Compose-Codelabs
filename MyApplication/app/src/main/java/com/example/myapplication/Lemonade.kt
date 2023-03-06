package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.flow.asFlow

class Lemonade:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp(){
    StepsOfLemonade(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Composable
fun StepsOfLemonade(modifier: Modifier=Modifier) {
    var result by remember {
        mutableStateOf(1)
    }
    var a by remember {
        mutableStateOf(1)
    }
    val image = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val discription = when (result) {
        1 -> R.string.dis1
        2 -> R.string.dis2
        3 -> R.string.dis3
        else -> R.string.dis4
    }
    val title = when (result) {
        1 -> R.string.step1
        2 -> R.string.step2
        3 -> R.string.step3
        else -> R.string.step4
    }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = title), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Image(painter = painterResource(id = image),
            contentDescription = stringResource(id = discription),
            modifier
                .wrapContentSize()
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    if (result == 4) {
                        result = 1
                        a=1
                    } else if (result == 2 && a != 4) {
                        a++
                        result = 2
                    } else {
                        result++
                    }
                })

    }

}

