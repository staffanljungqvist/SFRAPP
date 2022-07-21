package com.levento.sfrapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.levento.sfrapp.domain.Benefit
import com.levento.sfrapp.navigation.NavRoutes
import com.levento.sfrapp.screens.benefits.BenefitsViewModel
import com.levento.sfrapp.screens.screencomponents.BenefitRow
import com.levento.sfrapp.screens.screencomponents.ContentList
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import com.levento.sfrapp.ui.theme.backgroundColor

@Composable
fun BenefitsScreen(viewModel: BenefitsViewModel = viewModel(), navController: NavHostController) {

    val categoryList by remember { viewModel.populatedCategories }

    val onBenefitClick: (Benefit) -> Unit = { benefit ->
        navController.navigate(NavRoutes.BenefitDetailScreen.route + "/${benefit.id}") {
            launchSingleTop
        }
    }

    SFRAPPTheme() {
        Column(modifier = Modifier.background(backgroundColor)) {
            LazyColumn() {
                items(categoryList) { category ->
                    if (category.benefits.isNotEmpty()) {
                        ContentList(header = category.title!!) {
                            BenefitRow(
                                benefits = category.benefits,
                                categoryImage = category.imageURL!!,
                                onBenefitClick = onBenefitClick
                            )
                        }
                    }
                }
            }
        }
    }
}
