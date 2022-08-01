package com.levento.sfrapp.screens.screencomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.PlaceHolders
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import com.levento.sfrapp.ui.theme.brown
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior


@OptIn(ExperimentalSnapperApi::class)
@Composable
fun BenefitRow(
    benefits: List<Benefit>,
    categoryImage: String? = null,
    onBenefitClick: (Benefit) -> Unit
) {

    val lazyListState: LazyListState = rememberLazyListState()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 70.dp),
        state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(lazyListState),

    ) {
        items(benefits) { benefit ->
            BenefitCard(benefit = benefit, onClick = onBenefitClick, categoryImage = categoryImage)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BenefitCard(
    benefit: Benefit,
    categoryImage: String? = null,
    onClick: (Benefit) -> Unit
) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(200.dp)
            .width(250.dp),
        onClick = { onClick(benefit) }
        ) {
        Box(
            modifier = Modifier.background(Color.White)
        ) {
            Image(
                painter = if (categoryImage != null) {
                    rememberAsyncImagePainter(categoryImage)
                } else {
                    painterResource(id = PlaceHolders.categoryImage)
                },
                contentDescription = "",
                modifier = Modifier
                    .height(74.dp)
                    .width(250.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 37.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(benefit.imageURL),
                    contentDescription = "",
                    modifier = Modifier
                        .height(64.dp)
                        .width(140.dp)
                        .clip(shapes.small),
                    contentScale = ContentScale.Crop
                )
                Offer(benefit.dealtext1, benefit.dealtext2)
            }
        }
    }
}

@Composable
fun Offer(offerTitle: String, offerSubTitle: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = offerTitle.uppercase(),
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center
        )
        Text(
            text = offerSubTitle.uppercase(),
            fontWeight = FontWeight.SemiBold,
            color = brown,
            textAlign = TextAlign.Center
        )
    }
}

val placeHolderBenefit = Benefit(
    title = "Förmån",
    dealtext1 = "Rabatter på våra stationer",
    dealtext2 = "bensin, tvätt, hyrbil m.m"
)

@Preview(heightDp = 100, widthDp = 250)
@Composable
fun benefitRowPreview() {
    SFRAPPTheme() {
        BenefitRow(
            benefits = PlaceHolders.benefits
        ) { }
    }
}
