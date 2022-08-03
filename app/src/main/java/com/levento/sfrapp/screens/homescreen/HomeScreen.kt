package com.levento.sfrapp.screens

import MainViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.levento.sfrapp.models.Article
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.screens.screencomponents.BenefitRow
import com.levento.sfrapp.screens.screencomponents.ContentList
import com.levento.sfrapp.screens.screencomponents.NewsRow
import com.levento.sfrapp.models.PlaceHolders
import com.levento.sfrapp.navigation.NavRoutes
import com.levento.sfrapp.screens.homescreen.HomeViewModel
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import com.levento.sfrapp.ui.theme.backgroundColor
import com.levento.sfrapp.R


@Composable
fun HomeScreen(
    newsArticles: List<Article>,
    exclusiveBenefits: List<Benefit>,
    onBenefitClick: (Benefit) -> Unit,
    onArticleClick: (Article) -> Unit,

) {

    SFRAPPTheme() {
        Box {
            Image(
                painter = painterResource(id = R.drawable.background_image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier  = Modifier
                //    .background(backgroundColor)
                    .fillMaxHeight()
            ) {

                ContentList("Nyheter") {
                    NewsRow(newsArticles, onArticleClick)
                }

                ContentList(header = "Aktuella förmåner") {
                    BenefitRow(
                        benefits = exclusiveBenefits,
                        onBenefitClick = onBenefitClick
                    )
                }
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