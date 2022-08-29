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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.request.ImageResult
import com.levento.sfrapp.domain.model.Benefit
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
    onBenefitClick: (Benefit) -> Unit,
    modifier: Modifier = Modifier
) {

    val lazyListState: LazyListState = rememberLazyListState()

    Column(modifier = Modifier.padding(bottom = 10.dp, top = 24.dp)) {

        //    Spacer(modifier = Modifier.height(28.dp))

        Header(header, modifier = modifier.padding(horizontal = 16.dp))

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 60.dp),
            state = lazyListState,
            flingBehavior = rememberSnapperFlingBehavior(lazyListState),
        ) {

            items(benefits) { benefit ->
                BenefitCard(
                    benefit = benefit,
                    onClick = onBenefitClick,
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BenefitCard(
    benefit: Benefit,
    onClick: (Benefit) -> Unit
) {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(180.dp)
            .width(240.dp),
        onClick = { onClick(benefit) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val context = LocalContext.current
            val placeholderImage = R.drawable.fordon_kategori
            val imageUrl = benefit.imageURL

            val imageRequest = ImageRequest.Builder(context)
                .data(imageUrl)
                .memoryCacheKey(imageUrl)
                .diskCacheKey(imageUrl)
                .setHeader("Cache-Control", "public")
                .error(placeholderImage)
                .fallback(placeholderImage)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build()

            Spacer(Modifier.height(16.dp))

            AsyncImage(
                model = imageRequest,
                contentDescription = "",
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .height(74.dp)
                    .width(170.dp)
                    .clip(shapes.medium),
                contentScale = ContentScale.Crop,
                imageLoader = context.imageLoader
            )
            Column(
                modifier = Modifier

                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = benefit.dealtext1.uppercase(),
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = benefit.dealtext2.uppercase(),
                    style = MaterialTheme.typography.h3,
                    color = red,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun benefitRowPreview() {
    SFRAPPTheme() {
        BenefitRow(
            header = "Placeholder Header",
            benefits = Benefit.placeholderList,
            onBenefitClick = {}
        )
    }
}
