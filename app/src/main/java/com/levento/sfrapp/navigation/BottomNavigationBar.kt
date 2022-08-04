package com.levento.sfrapp.navigation

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.levento.sfrapp.CardActivity
import com.levento.sfrapp.ui.theme.*

@Composable
fun BottomNavigationBar(navController: NavController) {

    CustomBottomNavigation(
        backgroundColor = BottomBackgroundCOlor,
        modifier = Modifier.height(60.dp)

        ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        val context = LocalContext.current

        NavBarItems.BarItems.forEach { navItem ->

            val selected = currentRoute == navItem.route
            val cardItem = navItem.title == "Kort"


            BottomNavigationItem(
                selected = selected,
                onClick = {
                    if (cardItem) {
                        context.startActivity(Intent(context, CardActivity::class.java))
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
                            painter = painterResource(id = navItem.image),
                            contentDescription = "card icon",
                            tint = if (selected) selectedColor else unselectedColor,
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                                .size(25.dp),
                        )
                    }
                },
                label = if (!cardItem) {
                    {
                        Text(
                            text = navItem.title,
                          //  fontSize = 14.sp,
                            color = if (selected) selectedColor else unselectedColor,
                            modifier = Modifier
                                .padding(vertical = 5.dp)
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
    BottomNavigationBar(navController = rememberNavController())
}

