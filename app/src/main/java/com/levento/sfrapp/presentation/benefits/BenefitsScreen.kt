package com.levento.sfrapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.domain.model.Benefit
import com.levento.sfrapp.domain.model.BenefitCategory
import com.levento.sfrapp.screens.screencomponents.BenefitRow
import com.levento.sfrapp.ui.theme.SFRAPPTheme

@Composable
fun BenefitsScreen(
    categoryList: List<BenefitCategory>,
    onBenefitClick: (Benefit) -> Unit
) {
    SFRAPPTheme() {

        LazyColumn() {

            items(categoryList) { category ->
                if (category.benefits.isNotEmpty()) {
                    BenefitRow(
                        category.title!!,
                        benefits = category.benefits,
                        categoryImage = category.image,
                        onBenefitClick = onBenefitClick
                    )
                }
            }
        }
    }
}
