package com.levento.sfrapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.levento.sfrapp.domain.Article
import com.levento.sfrapp.domain.Benefit
import com.levento.sfrapp.screens.screencomponents.BenefitRow
import com.levento.sfrapp.screens.screencomponents.ContentList
import com.levento.sfrapp.screens.screencomponents.NewsRow
import com.levento.sfrapp.domain.PlaceHolders
import com.levento.sfrapp.navigation.NavRoutes
import com.levento.sfrapp.screens.homescreen.HomeViewModel
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import com.levento.sfrapp.ui.theme.backgroundColor


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navController: NavHostController,
) {

    val newsArticles by remember { viewModel.newsArticles }
    val exclusiveBenefits = remember { viewModel.exclusiveBenefits }

    val onBenefitClick: (Benefit) -> Unit = { benefit ->
        navController.navigate(NavRoutes.BenefitDetailScreen.route + "/${benefit.id}") {
            launchSingleTop
        }
    }

    val onArticleClick: (Article) -> Unit = { article ->
        navController.navigate(NavRoutes.ArticleDetailScreen.route + "/${article.title}") {
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
            }
        }
    }
}