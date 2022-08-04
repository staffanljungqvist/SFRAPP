package com.levento.sfrapp.screens.benefitdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.levento.sfrapp.R
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.screens.screencomponents.HTMLContentView
import com.levento.sfrapp.ui.theme.blue


@Composable
fun BenefitDetailScreen(benefit: Benefit) {

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
            placeholder = painterResource(R.drawable.empty_pic),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )

        HTMLContentView(htmlText = benefit.content ?: "Missing content")

        BenefitButton()

        Spacer(modifier = Modifier.height(100.dp))

    }
}


@Composable
fun BenefitButton(modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(backgroundColor = blue),
        modifier = modifier
    ) {
        Text("Ta del av förmån", color = Color.White, fontSize = 18.sp)
    }
}

@Preview
@Composable
fun BenefitButtonPreview() {
    BenefitButton()
}



