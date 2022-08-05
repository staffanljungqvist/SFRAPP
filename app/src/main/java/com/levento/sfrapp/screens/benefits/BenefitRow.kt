package com.levento.sfrapp.screens.screencomponents

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageResult
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.PlaceHolders
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import com.levento.sfrapp.ui.theme.brown
import com.levento.sfrapp.ui.theme.red
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior


@OptIn(ExperimentalSnapperApi::class)
@Composable
fun BenefitRow(
    header: String,
    benefits: List<Benefit>,
    categoryImage: ImageResult? = null,
    onBenefitClick: (Benefit) -> Unit
) {

    val lazyListState: LazyListState = rememberLazyListState()

    Column() {

        Text(
            text = header.uppercase(),
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(start = 40.dp, end = 40.dp),
            state = lazyListState,
            flingBehavior = rememberSnapperFlingBehavior(lazyListState),
        ) {

            items(benefits) { benefit ->
                BenefitCard(
                    benefit = benefit,
                    onClick = onBenefitClick,
                    categoryImage = categoryImage
                )
            }
        }

        Spacer(modifier = Modifier.padding(bottom = 28.dp))

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BenefitCard(
    benefit: Benefit,
    categoryImage: ImageResult? = null,
    onClick: (Benefit) -> Unit
) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(150.dp)
            .width(200.dp),
        onClick = { onClick(benefit) }
    ) {
        Box(
            modifier = Modifier.background(Color.White)
        ) {
/*            AsyncImage(
                model = categoryImage?.drawable,
                contentDescription = "",
                modifier = Modifier
                    .height(74.dp)
                    .width(250.dp),
                contentScale = ContentScale.Fit
            )*/
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = benefit.image?.drawable,
                    contentDescription = "",
                    modifier = Modifier
                        .height(64.dp)
                        .width(140.dp)
                        .clip(shapes.small),
                    contentScale = ContentScale.Crop
                )

                //Text för testsyfte. byt it med dealtext1 och dealtext2 när firebase är ifyllt.
                Offer("Erbjudande", "50% rabatt")
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
            color = red,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun OfferPreview() {
    BenefitCard(benefit = PlaceHolders.benefits[0], onClick = {})
}


@Preview(showBackground = true)
@Composable
fun benefitRowPreview() {
    SFRAPPTheme() {
        BenefitRow(
            header = "Placeholder Header",
            benefits = PlaceHolders.benefits
        ) { }
    }
}
