package com.levento.sfrapp.screens.screencomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.levento.sfrapp.R
import com.levento.sfrapp.models.Article
import com.levento.sfrapp.data.PlaceHolders
import com.levento.sfrapp.ui.theme.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior


//Todo byt ut articles till lista med Artikelobjet
@OptIn(ExperimentalSnapperApi::class)
@Composable
fun NewsRow(articles: List<Article>, onArticleClick: (Article) -> Unit) {

    val lazyListState: LazyListState = rememberLazyListState()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        //  contentPadding = PaddingValues(horizontal = 16.dp),
        state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(lazyListState)
    ) {
        items(articles) { article ->
            Column(
                modifier = Modifier
                    .fillParentMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NewsCard(article = article, onArticleClick = onArticleClick)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsCard(article: Article, onArticleClick: (Article) -> Unit) {
    Card(
        elevation = 1.dp,
        //   shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onArticleClick(article) }
    ) {

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                         //   dark.copy(alpha = 0.7f),
                            blue.copy(alpha = 0.1f),
                            Color.White,
                            Color.White,
                            Color.White
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()

            ) {



                ArticleThumbnail(article.imageUrl, modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 20.dp)
                    .clip(Shapes.small)
                )

                Text(
                    text = article.title ?: "Missing headline",
                    style = MaterialTheme.typography.h4,
                    color = red,
                    maxLines = 3,
                    modifier = Modifier
                        .height(60.dp)
                        .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 4.dp)
                    //      .weight(1.0f)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, bottom = 10.dp)
                ) {
                    Text(
                        text = "17-05-2022",
                       // style = MaterialTheme.typography.caption,
                        color = gray800
                    )

                    Text(
                        "Kategori",
                        modifier = Modifier.padding(start = 30.dp, end = 30.dp, bottom = 4.dp)
                    )
                }

            }
        }




    }
}

@Composable
fun ArticleThumbnail(image: String?, modifier: Modifier = Modifier) {
    AsyncImage(
        model = image,
        contentDescription = null,
        modifier = modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillWidth,
        placeholder = painterResource(id = R.drawable.placeholder_img),
        error = painterResource(id = R.drawable.placeholder_img)
    )
}

@Preview
@Composable
fun ArticlePreview() {
    //NewsCard(article = PlaceHolders.newsList[0], onArticleClick = {})
}