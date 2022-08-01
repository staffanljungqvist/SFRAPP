package com.levento.sfrapp.screens.screencomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.levento.sfrapp.models.Article
import com.levento.sfrapp.models.PlaceHolders
import com.levento.sfrapp.ui.theme.gray800
import com.levento.sfrapp.ui.theme.red
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior


//Todo byt ut articles till lista med Artikelobjet
@OptIn(ExperimentalSnapperApi::class)
@Composable
fun NewsRow(articles: List<Article>, onArticleClick: (Article) -> Unit) {

    val lazyListState: LazyListState = rememberLazyListState()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(lazyListState)
    ) {
        items(articles) { article ->
            NewsCard(article = article, onArticleClick = onArticleClick)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsCard(article: Article, onArticleClick: (Article) -> Unit) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(258.dp)
            .width(288.dp),
        onClick = { onArticleClick(article) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            ArticleThumbnail(article.imageUrl)
            Text(
                text = article.title!!,
                style = MaterialTheme.typography.h4,
                color = red,
                maxLines = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp, top = 8.dp)
                    .weight(1.0f)
            )

            //       Spacer(modifier = Modifier.fillMaxHeight())

            Text(
                text = "17-05-2022",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp),
                style = MaterialTheme.typography.caption,
                color = gray800
            )
        }
    }
}

@Composable
fun ArticleThumbnail(image: String?) {
    Image(
        painter = if (image == null) {
            painterResource(id = PlaceHolders.categoryImage)
        } else {
            rememberAsyncImagePainter(image)
        },
        contentDescription = null,
        modifier = Modifier
            .clip(shapes.small),
        contentScale = ContentScale.Fit
    )
}

@Preview
@Composable
fun ArticleThumbnailPreview() {
    NewsRow(articles = PlaceHolders.newsList, onArticleClick = {})
}