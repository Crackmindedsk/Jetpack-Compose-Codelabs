package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.SuperHeroData
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.data.SuperheroDataSource

class Superhero:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                SuperheroScreen()
            }
        }
    }
}
@Preview
@Composable
fun SuperheroScreen(){
    Scaffold(
        topBar = {SuperheroTopBar()}
    ) {
        LazyColumn(modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(it)){
            items(SuperheroDataSource().heroes){
                SuperheroCard(hero = it)
            }
        }
    }

}

@Composable
fun SuperheroIcon(@DrawableRes heroIcon:Int,modifier: Modifier){
    Image(painter = painterResource(id = heroIcon), contentDescription =null,
    modifier = modifier
        .size(72.dp)
        .padding(16.dp)
        .clip(RoundedCornerShape(8.dp))

          )
}


@Composable
fun SuperheroInfo(@StringRes title:Int, description:Int,modifier: Modifier=Modifier){
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = stringResource(id = title),
        style = MaterialTheme.typography.h3)
        Text(text = stringResource(id = description),
        style = MaterialTheme.typography.body1,)

    }
}
@Composable
fun SuperheroCard(hero:SuperHeroData){
    Card(modifier = Modifier
        .padding(8.dp)
        .clip(MaterialTheme.shapes.large)
        .fillMaxWidth(),
    elevation = 2.dp) {
        Row {
            SuperheroInfo(title = hero.nameRes, description = hero.descriptionRes, modifier = Modifier.weight(10f,false))
            Spacer(modifier = Modifier.weight(1f))
            SuperheroIcon(heroIcon = hero.imageRes, modifier =Modifier.align(Alignment.CenterVertically) )
        }

    }
}
@Composable
fun SuperheroTopBar(){
        Row(modifier = Modifier.height(57.dp).fillMaxWidth().background(color = MaterialTheme.colors.primary)) {
            Text(text = stringResource(id = R.string.superhero_app_name), modifier = Modifier.align(Alignment.CenterVertically).fillMaxWidth())

        }
}