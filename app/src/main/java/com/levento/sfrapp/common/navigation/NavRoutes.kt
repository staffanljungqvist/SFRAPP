package com.levento.sfrapp.common.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Benefits : NavRoutes("benefits")
    object Card : NavRoutes("card")
    object Profile : NavRoutes("profile")
    object Info : NavRoutes("info")
    object BenefitDetail : NavRoutes("benefitDetail")
    object ArticleDetail : NavRoutes("articleDetail")
    object AboutUs: NavRoutes("aboutUs")
    object Contact: NavRoutes("contact")
    object Gdpr: NavRoutes("gdpr")
}