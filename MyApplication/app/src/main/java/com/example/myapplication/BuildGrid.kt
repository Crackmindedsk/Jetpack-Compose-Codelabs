package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.BuildDataSource
import com.example.myapplication.model.BuildGridData
import com.example.myapplication.ui.theme.MyApplicationTheme

class BuildGrid: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            BuildGridScreen()
        }
    }
}
@Composable
fun BuildGridScreen(){
    MyApplicationTheme {
        BuildGridList(buildGrid = BuildDataSource().BuildLoad())

    }
}

@Preview
@Composable
fun BuildGridLOgic(){
    BuildGridCard(buildGrid = BuildGridData(R.string.affirmation1,R.drawable.image1,R.string.o1))
}

@Composable
fun BuildGridCard(buildGrid: BuildGridData,modifier: Modifier=Modifier){
    Card(modifier.padding(8.dp), elevation = 4.dp) {
        Row {
            Image(painter = painterResource(id = buildGrid.ImageResourceId) , contentDescription = stringResource(
                id = buildGrid.TitleResourceId
            ))
            Column {
                Text(text = stringResource(id =buildGrid.TitleResourceId) )
                Spacer(modifier = Modifier.height(7.dp))
                Text(text = stringResource(id = buildGrid.ValueResourceId ))
            }
        }
    }
}

@Composable
private fun BuildGridList(buildGrid:List<BuildGridData>){
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(3.dp)){
        items(buildGrid){
            build->
            BuildGridCard(buildGrid = build)
        }
    }
}