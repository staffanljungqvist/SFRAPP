package com.levento.sfrapp.screens

import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.levento.sfrapp.R
import com.levento.sfrapp.models.Article
import com.levento.sfrapp.models.PlaceHolders
import com.levento.sfrapp.screens.screencomponents.HTMLContentView
import com.levento.sfrapp.ui.theme.red


@Composable
fun ArticleDetailScreen(article: Article) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())

    ) {
        if (article.imageUrl == null) {
            Image(
                painter = painterResource(id = R.drawable.placeholder_img),
                contentDescription = null
            )
        } else {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder_img),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }
        
        ArticleHeadline(text = article.title ?: "Missing headline")

        HTMLContentView(
            htmlText = article.content ?: "Missing content"
        )
    }
}

@Composable
fun ArticleHeadline(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h1,
        color = red,
        maxLines = 3,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    )
}

@Preview
@Composable
fun ArticlePreview() {
    ArticleDetailScreen(article = PlaceHolders.newsList[0])
}