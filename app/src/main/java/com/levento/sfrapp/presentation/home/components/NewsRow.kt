package com.levento.sfrapp.presentation.home.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.levento.sfrapp.R
import com.levento.sfrapp.domain.model.Article
import com.levento.sfrapp.screens.Header
import com.levento.sfrapp.ui.theme.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import java.util.*


@OptIn(ExperimentalSnapperApi::class)
@Composable
fun NewsRow(articles: List<Article>, onArticleClick: (Article) -> Unit) {

    Column() {

        val lazyListState: LazyListState = rememberLazyListState()

        Spacer(modifier = Modifier.height(24.dp))
        Header("Nyheter", modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
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
}

@Composable
fun FeaturedPost(
    article: Article,
    onClick: (Article) -> Unit,
) {
    Card(elevation = 3.dp, modifier = Modifier.fillMaxHeight()) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .clickable { onClick(article) }
        ) {
            if (article.imageUrl != null) {
                AsyncImage(
                    model = article.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    placeholder = painterResource(id = R.drawable.placeholder_img),
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.placeholder_img),
                    contentDescription = null)
            }
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
fun NewsTitle(title: String) {

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
        append(divider)
        val tagStyle = MaterialTheme.typography.overline.toSpanStyle().copy(
            background = MaterialTheme.colors.secondary.copy(alpha = 0.1f)
        )
        article.tags.forEachIndexed { index, tag ->
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

@Preview
@Composable
fun ArticlePreview() {
    SFRAPPTheme() {
        NewsRow(articles = Article.placeholderList, onArticleClick = {})
    }
}