package com.levento.sfrapp.screens.screencomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.levento.sfrapp.R
import com.levento.sfrapp.common.PlaceHolders
import com.levento.sfrapp.feature_news.domain.model.Article
import com.levento.sfrapp.ui.theme.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import java.util.*


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
                FeaturedPost(article = article, onArticleClick)
            }
        }
    }
}

@Composable
fun FeaturedPost(
    article: Article,
    onClick: (Article) -> Unit,
    modifier: Modifier = Modifier.padding(16.dp)
) {
    Card(elevation = 3.dp, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(article) }
        ) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .heightIn(min = 200.dp)
                    .fillMaxWidth(),
            //    placeholder = painterResource(id = R.drawable.placeholder_img),
            )
            Spacer(Modifier.height(16.dp))

            val padding = Modifier.padding(horizontal = 16.dp)
            Box(modifier = padding.height(60.dp)) {
                NewsTitle(title = article.title ?: "")
            }


            Spacer(Modifier.height(8.dp))
            PostMetadata(article, padding)
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun NewsTitle(title: String, modifier: Modifier = Modifier) {

    val textStyleBody1 = MaterialTheme.typography.h6
    var textStyle by remember { mutableStateOf(textStyleBody1) }
    var readyToDraw by remember { mutableStateOf(false) }
    Text(
        text = title,
        style = textStyle,
        overflow = TextOverflow.Clip,
        modifier = Modifier.drawWithContent {
            if (readyToDraw) drawContent()
        },
        onTextLayout = { textLayoutResult ->
            if (textLayoutResult.didOverflowHeight) {
                textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9)
            } else {
                readyToDraw = true
            }
        }
    )
}

@Composable
private fun PostMetadata(
    article: Article,
    modifier: Modifier = Modifier
) {
    val divider = "  •  "
    val tagDivider = "  "
    val text = buildAnnotatedString {
        append(article.date?.dropLast(14) ?: "")
        append(divider)
     //   append(stringResource(R.string.read_time, post.metadata.readTimeMinutes))
        append(divider)
        val tagStyle = MaterialTheme.typography.overline.toSpanStyle().copy(
            background = MaterialTheme.colors.secondary.copy(alpha = 0.1f)
        )
        article.tags?.forEachIndexed { index, tag ->
            if (index != 0) {
                append(tagDivider)
            }
            withStyle(tagStyle) {
                append(" ${tag.uppercase(Locale.getDefault())} ")
            }
        }
    }
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = modifier
        )
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
    FeaturedPost(article = PlaceHolders.newsList[0], onClick = {})
}