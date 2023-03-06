package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Shapes

class ArtSpace:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MyApplicationTheme {
                ArtSpaceScreen()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ArtSpaceScreen(){
    var pic by remember {
        mutableStateOf(1)
    }
    val image = when(pic){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val discription = when (pic) {
        1 -> R.string.dis1
        2 -> R.string.dis2
        3 -> R.string.dis3
        else -> R.string.dis4
    }
    val title = when (pic) {
        1 -> R.string.step1
        2 -> R.string.step2
        3 -> R.string.step3
        else -> R.string.step4
    }
    Column(modifier=Modifier.padding(30.dp), verticalArrangement = Arrangement.SpaceEvenly) {
        Image(painter = painterResource(image) , contentDescription = stringResource(id = discription)
        , modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(500.dp)
                .border(
                    BorderStroke(2.dp, Color.Gray)
                ))
        Card(elevation = 10.dp, modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = stringResource(id = discription), fontSize = 20.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(stringResource(id = title), fontSize = 20.sp)
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(1f,false)
            , horizontalArrangement = Arrangement.SpaceAround ) {
            Button(onClick = { if(pic==1){
                pic=4
            }else{
                pic--
            }
            }) {
                Text("Previous")
            }
            Button(onClick = { if(pic==4){
                pic=1
            }else{
                pic++
            }
            }) {
                Text("Next")
            }
            
        }
    }
}