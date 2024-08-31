package com.ajidroid.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajidroid.amphibians.R
import com.ajidroid.amphibians.model.Amphibian

@Composable
fun HomeScreen(
    amphiUiState: AmphiUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
){
    when(amphiUiState){
        is AmphiUiState.Success -> DemoScreen(data = amphiUiState.data, modifier = Modifier.fillMaxWidth())
//            SuccessScreen(
//                amphibians = amphiUiState.data,
//                contentPadding = contentPadding
//            )
        is AmphiUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is AmphiUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }

}

@Composable
fun DemoScreen(data : String, modifier: Modifier = Modifier){
    Box (contentAlignment = Alignment.Center,
        modifier = modifier){
        Text(text = data)
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier){
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
        Image(
            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(text = stringResource(id = R.string.loading))
//    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_broken_image),
            contentDescription = null,
            modifier = Modifier.size(160.dp , 160.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(id = R.string.error))
    }
}

@Composable
fun SuccessScreen(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
){
    LazyColumn (contentPadding = contentPadding){
        items(amphibians){amphibian ->
            AmphibianCard(
                amphibian = amphibian,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
    ){
    Card(modifier) {
        Column(modifier) {
            Text(text = amphibian.name)



            Text(text = amphibian.description)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewLoading(){
    LoadingScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewError(){
    ErrorScreen()
}