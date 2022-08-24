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

    //    Spacer(modifier = Modifier.height(28.dp))

      //  Header(header, modifier = Modifier.padding(horizontal = 16.dp))



        LazyColumn() {
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
                Spacer(Modifier.height(50.dp))
            }
        }
    }
}


@Composable
fun Header(
    title: String,
    modifier: Modifier = Modifier
) {

    Text(
        text = title.uppercase(),
        style = MaterialTheme.typography.h1,
        modifier = modifier
            .fillMaxWidth()
    )
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SFRAPPTheme() {
        HomeScreen(PlaceHolders.newsList, Benefit.placeholderList, {}, {})
    }
}
