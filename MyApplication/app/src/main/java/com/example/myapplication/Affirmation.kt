package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.DataSource
import com.example.myapplication.model.AffirmationData
import com.example.myapplication.ui.theme.MyApplicationTheme

class Affirmation:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationScreen()
        }
    }
}

@Composable
fun AffirmationScreen(){
    val context= LocalContext.current
    MyApplicationTheme {
        AffirmationList(affirmationList = DataSource().load())
    }
}
@Preview
@Composable
fun AffirmationPreview(){
    AffirmationCard(affirmation = AffirmationData(R.string.affirmation1, R.drawable.image1))

}
@Composable
fun AffirmationCard(affirmation: AffirmationData, modifier: Modifier=Modifier){
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp) {
        Column {
            Image(painter = painterResource(id = affirmation.imageResourceId), contentDescription = stringResource(
                id = affirmation.stringResourceId
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(194.dp),
            contentScale = ContentScale.Crop)
            Text(text = stringResource(id = affirmation.stringResourceId),
            modifier = Modifier.padding(16.dp),
            style= MaterialTheme.typography.h6)
        }

    }

}
@Composable
private fun AffirmationList(affirmationList:List<AffirmationData>,modifier: Modifier=Modifier){
    LazyColumn{
        items(affirmationList){
            affirmation-> AffirmationCard(affirmation = affirmation)
        }
    }

}