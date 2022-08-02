package com.levento.sfrapp

import MainViewModel
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firestore.v1.TransactionOptionsOrBuilder
import com.levento.sfrapp.models.Article
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.PlaceHolders
import com.levento.sfrapp.navigation.BottomNavigationBar
import com.levento.sfrapp.navigation.NavRoutes
import com.levento.sfrapp.navigation.TopBar
import com.levento.sfrapp.screens.*
import com.levento.sfrapp.screens.benefitdetail.BenefitDetailScreen
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import com.levento.sfrapp.ui.theme.backgroundColor

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.load()

        setContent {

            val isLoading by viewModel.isLoading.collectAsState()

            SFRAPPTheme {
                when {
                    isLoading -> SplashScreen()
                    else -> MainScreen(viewModel)
                }
            }
        }
    }
}


@Composable
fun MainScreen(viewModel: MainViewModel) {

    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = { TopBar(currentRoute) },
        content = {
            NavigationHost(
                navController = navController,
                viewModel = viewModel
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    viewModel: MainViewModel
) {

    val newsArticles by remember { viewModel.articles }
    val exclusiveBenefits by remember { viewModel.exclusiveBenefits }

    val currentArticle by remember { viewModel.currentArticle }
    val currentBenefit by remember { viewModel.currentBenefit }
    val categoryList by remember { viewModel.populatedCategories }

    val onBenefitClick: (Benefit) -> Unit = { benefit ->
        viewModel.setCurrentBenefit(benefit)
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


    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) {
            HomeScreen(
                newsArticles = newsArticles,
                exclusiveBenefits = exclusiveBenefits,
                onArticleClick = onArticleClick,
                onBenefitClick = onBenefitClick
            )
        }

        composable(NavRoutes.ArticleDetailScreen.route) { backStackEntry ->
            ArticleDetailScreen(currentArticle)
        }

        composable(NavRoutes.BenefitDetailScreen.route) { backStackEntry ->
            BenefitDetailScreen(currentBenefit)
        }

        composable(NavRoutes.Benefits.route) {
            BenefitsScreen(categoryList = categoryList, onBenefitClick = onBenefitClick)
        }

        composable(NavRoutes.Card.route) {

        }

        composable(NavRoutes.Profile.route) {
            ProfileScreen(PlaceHolders.userCompany)
        }
        composable(NavRoutes.Info.route) {
            InfoScreen()
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    SFRAPPTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
               MainScreen(viewModel = MainViewModel(SFRAPP()))
        }
    }
}