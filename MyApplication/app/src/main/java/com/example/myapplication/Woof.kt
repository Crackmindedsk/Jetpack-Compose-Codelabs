package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.Dog
import com.example.myapplication.data.dogs
import com.example.myapplication.ui.theme.MyApplicationTheme

class Woof:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                WoofApp()
            }
        }
    }
}
@Composable
fun WoofApp() {
    LazyColumn {
        items(dogs) {
            DogItem(dog = it)
        }
    }
}
@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        DogIcon(dog.imageResourceId)
        DogInformation(dog.name, dog.age)
    }
}
@Composable
fun DogIcon(@DrawableRes dogIcon: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(64.dp)
            .padding(8.dp),
        painter = painterResource(dogIcon),
        contentDescription = null
    )
}
@Composable
fun DogInformation(@StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(dogName),
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(R.string.years_old, dogAge)
        )
    }
}
@Preview
@Composable
fun WoofPreview() {
    MyApplicationTheme(darkTheme = false) {
        WoofApp()
    }
}