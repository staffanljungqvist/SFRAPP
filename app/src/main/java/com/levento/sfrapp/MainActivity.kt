package com.levento.sfrapp

import MainViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.levento.sfrapp.models.PlaceHolders
import com.levento.sfrapp.navigation.BottomNavigationBar
import com.levento.sfrapp.navigation.NavRoutes
import com.levento.sfrapp.screens.*
import com.levento.sfrapp.screens.benefitdetail.BenefitDetailScreen
import com.levento.sfrapp.ui.theme.SFRAPPTheme

class MainActivity : ComponentActivity() {
    //  private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SFRAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {

    val navController = rememberNavController()
    val currentArticle by remember { mutableStateOf(viewModel.currentArticle) }

    Scaffold(
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

    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        composable(NavRoutes.ArticleDetailScreen.route) { backStackEntry ->
            ArticleDetailScreen(viewModel)

/*            val articleId = backStackEntry.arguments?.getString("articleTitle")
            articleId?.let { title ->
                ArticleDetailScreen(title)
            }*/
        }

        composable(NavRoutes.BenefitDetailScreen.route) { backStackEntry ->

            BenefitDetailScreen(viewModel)

/*            val benefitId = backStackEntry.arguments?.getString("benefitId")
            benefitId?.let { id ->
                BenefitDetailScreen(id)
            }*/
        }

        composable(NavRoutes.Benefits.route) {
            BenefitsScreen(navController = navController, viewModel = viewModel)
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
            //   MainScreen()
        }
    }
}