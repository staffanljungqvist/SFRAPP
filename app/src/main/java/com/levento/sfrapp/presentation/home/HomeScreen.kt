package com.levento.sfrapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.domain.model.Article
import com.levento.sfrapp.domain.model.Benefit
import com.levento.sfrapp.domain.util.PlaceHolders
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

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom ) {

            Row(Modifier.fillMaxWidth().weight(1f),
            ) {
                NewsRow(newsArticles, onArticleClick)
            }

            BenefitRow(
                header = "Aktuella förmåner",
                benefits = exclusiveBenefits,
                onBenefitClick = onBenefitClick,
            )

            Spacer(Modifier.height(16.dp))
        }

    //

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
