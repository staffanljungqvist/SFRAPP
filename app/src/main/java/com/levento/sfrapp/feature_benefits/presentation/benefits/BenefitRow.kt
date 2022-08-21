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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.request.ImageResult
import com.levento.sfrapp.feature_benefits.domain.model.Benefit
import com.levento.sfrapp.common.PlaceHolders
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import com.levento.sfrapp.ui.theme.red
import com.levento.sfrapp.R
import com.levento.sfrapp.screens.Header
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

        Header(header)

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

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
        elevation = 3.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(180.dp)
            .width(220.dp),
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
                val context = LocalContext.current
                val placeholderImage = R.drawable.placeholder_img
                val imageUrl = benefit.imageURL

                val imageRequest = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .memoryCacheKey(imageUrl)
                    .diskCacheKey(imageUrl)

                    .setHeader("Cache-Control", "public", )
                    .error(placeholderImage)
                    .fallback(placeholderImage)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build()

/*                AsyncImage(
                    model = imageRequest,
                    modifier = Modifier.size(64.dp),
                    contentDescription = null,
                    imageLoader = context.imageLoader
                )*/

                AsyncImage(
                    model = imageRequest,
                    contentDescription = "",
                    modifier = Modifier
                        .height(74.dp)
                        .width(160.dp)
                        .clip(shapes.medium),
                    contentScale = ContentScale.Crop,
                    imageLoader = context.imageLoader
                )

                //Text för testsyfte. byt it med dealtext1 och dealtext2 när firebase är ifyllt.
                Offer(benefit.dealtext1, "50% rabatt")
            }
        }
    }
}

@Composable
fun Offer(offerTitle: String, offerSubTitle: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 8.dp, end = 8.dp),
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
    BenefitCard(benefit = Benefit.placeholder, onClick = {})
}


@Preview(showBackground = true)
@Composable
fun benefitRowPreview() {
    SFRAPPTheme() {
        BenefitRow(
            header = "Placeholder Header",
            benefits = Benefit.placeholderList
        ) { }
    }
}
