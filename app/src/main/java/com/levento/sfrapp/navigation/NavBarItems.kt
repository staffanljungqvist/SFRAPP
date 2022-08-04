package com.levento.sfrapp.navigation

import com.levento.sfrapp.R

object NavBarItems {

    private val defaultIcons = false

    val BarItems = listOf(
        BarItem(
            title = "Hem",
            image = if (defaultIcons) R.drawable.ic_baseline_home_24 else R.drawable.icon_home,
            route = NavRoutes.Home.route
        ),
        BarItem(
            title = "Förmåner",
            image = if (defaultIcons) R.drawable.ic_baseline_star_24 else R.drawable.icon_benefits,
            route = NavRoutes.Benefits.route
        ),
        BarItem(
            title = "Kort",
            image = R.drawable.icon_card_big,
            route = NavRoutes.Card.route,
        ),
        BarItem(
            title = "Min sida",
            image = if (defaultIcons) R.drawable.ic_baseline_person_24 else R.drawable.icon_profile,
            route = NavRoutes.Profile.route
        ),
        BarItem(
            title = "Info",
            image = if (defaultIcons) R.drawable.ic_baseline_info_24 else R.drawable.icon_info,
            route = NavRoutes.Info.route
        ),
    )
}