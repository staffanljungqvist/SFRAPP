package com.levento.sfrapp.screens

import MainViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import com.levento.sfrapp.ui.theme.backgroundColor


@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    navController: NavHostController,
) {

    val newsArticles by remember { viewModel.articles }
    val exclusiveBenefits by remember { viewModel.exclusiveBenefits }

    val onBenefitClick: (Benefit) -> Unit = { benefit ->

        viewModel.setCurrentBenefit(benefit)
        navController.navigate(NavRoutes.Benefits.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        navController.navigate(NavRoutes.BenefitDetailScreen.route) {
            launchSingleTop
        }
    }

    val onArticleClick: (Article) -> Unit = { article ->
        viewModel.setCurrentArticle(article)
        navController.navigate(NavRoutes.ArticleDetailScreen.route) {
            launchSingleTop
        }
    }

    SFRAPPTheme() {
        Column(
            modifier = Modifier
                .background(backgroundColor)
                .fillMaxHeight()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )

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