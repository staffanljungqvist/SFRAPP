package com.levento.sfrapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.models.Article
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.screens.screencomponents.BenefitRow
import com.levento.sfrapp.screens.screencomponents.ContentList
import com.levento.sfrapp.screens.screencomponents.NewsRow
import com.levento.sfrapp.models.PlaceHolders
import com.levento.sfrapp.R
import com.levento.sfrapp.ui.theme.*


@Composable
fun HomeScreen(
    newsArticles: List<Article>,
    exclusiveBenefits: List<Benefit>,
    onBenefitClick: (Benefit) -> Unit,
    onArticleClick: (Article) -> Unit,

) {

    SFRAPPTheme() {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.test_bakgrund),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier  = Modifier
                    //    .background(backgroundColor)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "NYHETER",
                    style = MaterialTheme.typography.h1,
                    color = brown,
                    modifier = Modifier.padding(start = 16.dp, bottom = 5.dp, top = 15.dp)
                )
            }
                    NewsRow(newsArticles, onArticleClick)

                    Spacer(Modifier.height(30.dp))

                ContentList(header = "Aktuella förmåner") {
                    BenefitRow(
                        benefits = exclusiveBenefits,
                        onBenefitClick = onBenefitClick
                    )
                }
                Spacer(Modifier.height(500.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SFRAPPTheme() {
        Column {
            ContentList("Nyheter") {
                NewsRow(PlaceHolders.newsList, onArticleClick = {})
            }
            ContentList(header = "Aktuella förmåner") {
                BenefitRow(benefits = PlaceHolders.benefits, onBenefitClick = {})
            }
        }
    }
}