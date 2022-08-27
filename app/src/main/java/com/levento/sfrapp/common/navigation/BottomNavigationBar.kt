package com.levento.sfrapp.common.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.levento.sfrapp.R
import com.levento.sfrapp.common.navigation.NavBarItems.BarItemsLoggedIn
import com.levento.sfrapp.ui.theme.BottomBackgroundCOlor
import com.levento.sfrapp.ui.theme.selectedColor
import com.levento.sfrapp.ui.theme.unselectedColor


@Composable
fun BottomNavigationBar(
    navController: NavController,
    loggedIn: Boolean,
    onCardLoggedIn: () -> Unit
) {

    CustomBottomNavigation(
        backgroundColor = BottomBackgroundCOlor,
        modifier = Modifier.height(70.dp)

    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        val barItems = BarItemsLoggedIn

        barItems.forEach { navItem ->

            val selected = currentRoute == navItem.route
            val cardItem = navItem.title == "Kort"

            BottomNavigationItem(
                selected = selected,
                onClick = {
                    if (cardItem) {
                        if (loggedIn) {
                            onCardLoggedIn()
                        } else {
                            navController.navigate(NavRoutes.Profile.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                            }
                        }
                    } else {
                        navController.navigate(navItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    if (cardItem) {
                        Icon(
                            painter = painterResource(id = navItem.image),
                            contentDescription = "card icon",
                            tint = Color.Unspecified,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(
                                id = if (navItem.route == "profile" && !loggedIn) R.drawable.ic_baseline_login_24
                                else navItem.image
                            ),
                            contentDescription = "card icon",
                            tint = if (selected) selectedColor else unselectedColor,
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .size(30.dp),
                        )
                    }
                },
                label = if (!cardItem) {
                    {
                        Text(
                            text = if (navItem.route == "profile" && !loggedIn) "Logga in" else navItem.title,
                            color = if (selected) selectedColor else unselectedColor,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(vertical = 7.dp)
                        )
                    }
                } else null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController(), true, onCardLoggedIn = {})
}

