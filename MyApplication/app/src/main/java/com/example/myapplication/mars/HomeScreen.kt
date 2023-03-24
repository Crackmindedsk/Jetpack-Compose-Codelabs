package com.example.myapplication.mars

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.network.MarsPhoto
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.serialization.json.JsonNull.content

@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    retryAction:()->Unit,
    modifier: Modifier = Modifier
) {
    when(marsUiState){
        is MarsUiState.Loading -> LoadingScreen(modifier=modifier)
        is MarsUiState.Success -> PhotosGridScreen(photos = marsUiState.photo,modifier=modifier)
        else -> ErrorScreen(retryAction=retryAction,modifier=modifier)
    }
}

@Composable
fun PhotosGridScreen(photos:List<MarsPhoto>,modifier: Modifier=Modifier){
    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp),
        modifier = modifier.fillMaxWidth() ){
        items(items=photos, key={photos->photos.id}){
            photos->
            MarsPhotoCard(photo = photos)
        }
    }
}

@Composable
fun MarsPhotoCard(photo: MarsPhoto, modifier: Modifier=Modifier){
    Card(modifier = modifier
        .padding(4.dp)
        .fillMaxWidth()
        .aspectRatio(1f),
    elevation = 8.dp) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.mars_photo),
            contentScale = ContentScale.FillBounds,
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.ic_loading_image))
    }

}

@Composable
fun ErrorScreen(retryAction: () -> Unit,modifier: Modifier=Modifier) {
    Column(modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = stringResource(id = R.string.loading_failed))
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier=Modifier) {
    Box(modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center){
        Image(
            modifier=Modifier.size(200.dp),
            painter=painterResource(id = R.drawable.ic_loading_image),
            contentDescription = stringResource(id = R.string.loading)
        )
    }

}

/**
 * The home screen displaying result of fetching photos.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(photos)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    MyApplicationTheme {
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}