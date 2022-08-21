package com.levento.sfrapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.levento.sfrapp.models.Article
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.BenefitCategory
import com.levento.sfrapp.navigation.BottomNavigationBar
import com.levento.sfrapp.navigation.NavRoutes
import com.levento.sfrapp.navigation.TopBar
import com.levento.sfrapp.screens.*
import com.levento.sfrapp.screens.benefitdetail.BenefitDetailScreen
import com.levento.sfrapp.screens.info.ContactScreen
import com.levento.sfrapp.screens.info.GdprScreen
import com.levento.sfrapp.screens.profile.LoginScreen
import com.levento.sfrapp.screens.profile.ProfileScreen
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TAG = "mydebug"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.load()

        setContent {

            val isLoading by viewModel.isLoading.collectAsState()

            SFRAPPTheme {
                if (isLoading) {
                    SplashScreen()
                } else MainScreen(viewModel)
            }
        }
    }
}


@Composable
fun MainScreen(viewModel: MainViewModel) {

    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val context = LocalContext.current
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val isLoggedIn by remember { viewModel.isLoggedIn }
    val newsArticles by remember { viewModel.articles }
    val currentArticle by remember { viewModel.currentArticle }
    val currentBenefit by remember { viewModel.currentBenefit }
    val categoryList by remember { viewModel.populatedCategories }

    val checkLoginStatus: () -> Unit = {
        scope.launch() {
            viewModel.checkLoginStatus()
        }
    }


    val onBenefitClick: (Benefit) -> Unit = { benefit ->
        viewModel.setCurrentBenefit(benefit)
        navController.navigate(NavRoutes.BenefitDetail.route) {
            launchSingleTop
        }
    }

    val onArticleClick: (Article) -> Unit = { article ->
        viewModel.setCurrentArticle(article)
        navController.navigate(NavRoutes.ArticleDetail.route) {
            launchSingleTop
        }
    }

    val onCardClickLoggedIn: () -> Unit = {
        val user = viewModel.user.value
        Log.d(TAG, "Klickade på kort")
        if (user.orgNr != null && user.companyName != null && user.expirationDate != null) {
            val i = Intent(context, CardActivity::class.java)
            i.putExtra("orgNumber", user.orgNr)
            i.putExtra("companyName", user.companyName)
            i.putExtra("expirationDate", user.expirationDate)
            context.startActivity(i)
        }
    }

    val showSnackBar: (String) -> Unit = { message ->
        scope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(currentRoute) },
        content = {
            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.padding(it)) {
                Image(
                    painter = painterResource(id = R.drawable.screen_background),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight(),
                    // .padding(bottom = 30.dp),
                    contentScale = ContentScale.FillHeight
                )

                NavigationHost(
                    navController = navController,
                    viewModel = viewModel,
                    isLoggedIn = isLoggedIn,
                    newsArticles = newsArticles,
                    categoryList = categoryList,
                    currentArticle = currentArticle,
                    currentBenefit = currentBenefit,
                    onArticleClick = onArticleClick,
                    onBenefitClick = onBenefitClick,
                    showSnackbar = showSnackBar,
                    checkLoginStatus = checkLoginStatus
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                loggedIn = isLoggedIn,
                onCardLoggedIn = onCardClickLoggedIn
            )
        }
    )
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    isLoggedIn: Boolean,
    newsArticles: List<Article>,
    categoryList: List<BenefitCategory>,
    currentArticle: Article,
    currentBenefit: Benefit,
    onArticleClick: (Article) -> Unit,
    onBenefitClick: (Benefit) -> Unit,
    showSnackbar: (String) -> Unit,
    checkLoginStatus: () -> Unit,
) {


    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) {
            HomeScreen(
                newsArticles = newsArticles,
                exclusiveBenefits = categoryList[0].benefits,
                onArticleClick = onArticleClick,
                onBenefitClick = onBenefitClick
            )
        }

        composable(NavRoutes.ArticleDetail.route) { backStackEntry ->
            ArticleDetailScreen(currentArticle)
        }

        composable(NavRoutes.BenefitDetail.route) { backStackEntry ->
            BenefitDetailScreen(currentBenefit, showSnackbar)
        }

        composable(NavRoutes.Benefits.route) {
            BenefitsScreen(categoryList = categoryList, onBenefitClick = onBenefitClick)
        }

        composable(NavRoutes.Card.route) {

        }

        composable(NavRoutes.Profile.route) {
            ProfileScreen(
                user = viewModel.user.value,
                checkLoginStatus = checkLoginStatus,
                isLoggedIn = isLoggedIn
            )
        }
        composable(NavRoutes.Info.route) {
            InfoScreen(navController)
        }
        composable(NavRoutes.AboutUs.route) {
            AboutScreen()
        }
        composable(NavRoutes.Contact.route) {
            ContactScreen()
        }
        composable(NavRoutes.Gdpr.route) {
            GdprScreen()
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
            //         MainScreen(viewModel = viewModel by )
        }
    }
}