package com.ajidroid.amphibians.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ajidroid.amphibians.R
import com.ajidroid.amphibians.model.Amphibian

@Composable
fun HomeScreen(
    amphiUiState: AmphiUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
){
    when(amphiUiState){
        is AmphiUiState.Success ->
            SuccessScreen(
                amphibians = amphiUiState.data,
                contentPadding = contentPadding
            )
        is AmphiUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is AmphiUiState.Error -> ErrorScreen(retryAction = retryAction, modifier = Modifier.fillMaxSize())
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
fun ErrorScreen(retryAction : () -> Unit, modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null,
            modifier = Modifier.size(160.dp , 160.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.error),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun AmphibianCard(
    amphibian: Amphibian,
//    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
    ){
    var expanded  by remember { mutableStateOf(false) }
    
    Card(
        onClick = { expanded = !expanded },
        modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = modifier) {
            Text(
                text = amphibian.name,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    ) {
                        append("Type:- ")
                    }
                    append(amphibian.type)
                },
                fontSize = 16.sp,
//                modifier = Modifier.padding()
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.image)
                    .crossfade(true)
                    .build(),
                modifier = Modifier.fillMaxWidth(),
                placeholder = painterResource(id = R.drawable.loading_img),
                error = painterResource(id = R.drawable.ic_broken_image),
                contentDescription = amphibian.name,
                contentScale = ContentScale.FillWidth,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ){
                        append("Description:- ")
                    }
                    append(amphibian.description)
                },
//                modifier = Modifier.padding(horizontal = 4.dp),
                maxLines = if (!expanded) 1 else Int.MAX_VALUE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewLoading(){
    LoadingScreen()
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewError(){
//    ErrorScreen()
//}