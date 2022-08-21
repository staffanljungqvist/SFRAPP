package com.levento.sfrapp.common.navigation

import com.levento.sfrapp.R

object NavBarItems {

    private val defaultIcons = true

    val BarItemsLoggedIn = listOf(
        BarItem(
            title = "HEM",
            image = if (defaultIcons) R.drawable.ic_baseline_home_24 else R.drawable.icon_home,
            route = NavRoutes.Home.route
        ),
        BarItem(
            title = "FÖRMÅNER",
            image = if (defaultIcons) R.drawable.ic_baseline_star_24 else R.drawable.icon_benefits,
            route = NavRoutes.Benefits.route
        ),
        BarItem(
            title = "Kort",
            image = R.drawable.icon_card_big,
            route = NavRoutes.Card.route,
        ),
        BarItem(
            title = "MIN SIDA",
            image = if (defaultIcons) R.drawable.ic_baseline_person_24 else R.drawable.icon_profile,
            route = NavRoutes.Profile.route
        ),
        BarItem(
            title = "INFO",
            image = if (defaultIcons) R.drawable.ic_baseline_info_24 else R.drawable.icon_info,
            route = NavRoutes.Info.route
        ),
    )
}