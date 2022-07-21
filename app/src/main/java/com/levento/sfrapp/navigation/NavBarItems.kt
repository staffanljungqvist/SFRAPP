package com.levento.sfrapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.levento.sfrapp.R

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title ="Hem",
            image = Icons.Filled.Home,
            route = NavRoutes.Home.route
        ),
        BarItem(
            title ="Förmåner",
            image = Icons.Filled.Star,
            route = NavRoutes.Benefits.route
        ),
        BarItem(
            title ="Kort",
            image = Icons.Filled.Settings,
            route = NavRoutes.Card.route,
            drawable = R.drawable.icon_card_big
        ),
        BarItem(
            title ="Min sida",
            image = Icons.Filled.Person,
            route = NavRoutes.Profile.route
        ),
        BarItem(
            title ="Info",
            image = Icons.Filled.Info,
            route = NavRoutes.Info.route
        ),
    )
}