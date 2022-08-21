package com.levento.sfrapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.feature_news.domain.model.Article
import com.levento.sfrapp.feature_benefits.domain.model.Benefit
import com.levento.sfrapp.common.PlaceHolders
import com.levento.sfrapp.screens.screencomponents.BenefitRow
import com.levento.sfrapp.screens.screencomponents.NewsRow
import com.levento.sfrapp.ui.theme.SFRAPPTheme


@Composable
fun HomeScreen(
    newsArticles: List<Article>,
    exclusiveBenefits: List<Benefit>,
    onBenefitClick: (Benefit) -> Unit,
    onArticleClick: (Article) -> Unit,
) {
    SFRAPPTheme() {


/*            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "NYHETER",
                    style = MaterialTheme.typography.h1,
                    color = brown,
                    modifier = Modifier.padding(start = 16.dp, bottom = 5.dp, top = 15.dp)
                )
            }*/

        LazyColumn() {
            item {
                Header("Nyheter")
            }
            item {
                NewsRow(newsArticles, onArticleClick)
            }
            item {
                BenefitRow(
                    header = "Aktuella förmåner",
                    benefits = exclusiveBenefits,
                    onBenefitClick = onBenefitClick
                )
            }
            item {
                Spacer(Modifier.height(500.dp))
            }
        }
    }
}


@Composable
fun Header(
    text: String,
    modifier: Modifier = Modifier
) {

    Text(
        text = text.uppercase(),
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SFRAPPTheme() {
        HomeScreen(PlaceHolders.newsList, Benefit.placeholderList, {}, {})
    }
}
