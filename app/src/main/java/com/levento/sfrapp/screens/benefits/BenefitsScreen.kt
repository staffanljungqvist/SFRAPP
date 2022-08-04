package com.levento.sfrapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.R
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.BenefitCategory
import com.levento.sfrapp.screens.screencomponents.BenefitRow
import com.levento.sfrapp.screens.screencomponents.ContentList
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import com.levento.sfrapp.ui.theme.screenBackgroundColor

@Composable
fun BenefitsScreen(
    categoryList: List<BenefitCategory>,
    onBenefitClick: (Benefit) -> Unit
) {

    SFRAPPTheme() {
        Box {
            Image(
                painter = painterResource(id = R.drawable.test_bakgrund),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column() {
                LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)) {
                    items(categoryList) { category ->
                        if (category.benefits.isNotEmpty()) {
                            ContentList(header = category.title!!) {
                                BenefitRow(
                                    benefits = category.benefits,
                                    categoryImage = category.image,
                                    onBenefitClick = onBenefitClick
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
