package com.levento.sfrapp.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
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

@Composable
fun BottomNavigationBar(navController: NavController) {

    BottomNavigation(backgroundColor = Color.White, modifier = Modifier.height(85.dp)) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->

            if (navItem.drawable != null) {
                BottomNavigationItem(
                    selected = currentRoute == navItem.route,
                    onClick = {},
                    icon = {
                        Icon(
                            painter = painterResource(id = navItem.drawable),
                            contentDescription = "card icon",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .padding(10.dp)

                        )
                    }
                )
            } else {
                BottomNavigationItem(
                    selected = currentRoute == navItem.route,
                    onClick = {
                        navController.navigate(navItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = navItem.image,
                            contentDescription = navItem.title,
                            modifier = Modifier.padding(10.dp)
                        )
                    },
                    label = {
                        Text(text = navItem.title, fontSize = 10.sp)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}