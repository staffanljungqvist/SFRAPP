package com.levento.sfrapp.navigation

sealed class NavRoutes(val route: String) {
    object Home: NavRoutes("home")
    object Benefits: NavRoutes("benefits")
    object Card: NavRoutes("card")
    object Profile: NavRoutes("profile")
    object Info: NavRoutes("info")
    object BenefitDetailScreen: NavRoutes("benefitDetail")
    object ArticleDetailScreen: NavRoutes("articleDetail")
}