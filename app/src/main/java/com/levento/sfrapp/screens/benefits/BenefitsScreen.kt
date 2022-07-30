package com.levento.sfrapp.screens

import MainViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
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
fun BenefitsScreen(viewModel: MainViewModel, navController: NavHostController) {

    val categoryList by remember { viewModel.populatedCategories }

    val onBenefitClick: (Benefit) -> Unit = { benefit ->
        viewModel.setCurrentBenefit(benefit)
        navController.navigate(NavRoutes.BenefitDetailScreen.route) {
            launchSingleTop
        }
    }

    SFRAPPTheme() {
        Column(modifier = Modifier.background(backgroundColor)) {
            LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)) {
                items(categoryList) { category ->
                    if (category.benefits.isNotEmpty()) {
                        ContentList(header = category.title!!) {
                            BenefitRow(
                                benefits = category.benefits,
                                categoryImage = category.imageURL,
                                onBenefitClick = onBenefitClick
                            )
                        }
                    }
                }
            }
        }
    }
}
