package com.levento.sfrapp.screens.benefitdetail

import MainViewModel
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.levento.sfrapp.domain.Benefit
import com.levento.sfrapp.domain.PlaceHolders
import com.levento.sfrapp.R


@Composable
fun BenefitDetailScreen(viewModel: MainViewModel) {

    val benefit by remember { viewModel.currentBenefit }

/*    LaunchedEffect(benefitId) {
        viewModel.getBenefit(benefitId)
    }*/

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())

    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(benefit.imageURL)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.fordon_kategori),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )

        HTMLContentView(
            htmlText = benefit.content ?: "Missing content"
        )
    }
}

@Composable
fun HTMLContentView(htmlText: String) {

    Box(Modifier.padding(20.dp).fillMaxHeight()) {
        val htmlDescription = remember(htmlText) {
            HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }

        AndroidView(
            factory = { context ->
                TextView(context).apply {
                    //   movementMethod = LinkMovementMethod.getInstance()
                }
            },
            update = {
                it.text = htmlDescription
            }
        )
    }

}

@Composable
fun BenefitContent(benefit: Benefit) {
    benefit.content?.let {
        HTMLContentView(it)
    }
}
