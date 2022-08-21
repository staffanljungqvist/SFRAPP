package com.levento.sfrapp.screens.benefitdetail

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.levento.sfrapp.R
import com.levento.sfrapp.TAG
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.screens.screencomponents.HTMLContentView
import com.levento.sfrapp.screens.screencomponents.HTMLContentView2
import com.levento.sfrapp.ui.theme.blue
import kotlinx.coroutines.launch


@Composable
fun BenefitDetailScreen(
    benefit: Benefit,
    showSnackbar: (String) -> Unit,
    viewModel: BenefitDetailViewModel = hiltViewModel(),
    ) {

    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(benefit.accessLink)) }
    val isLoggedIn by remember { viewModel.isLoggedIn }
    val scope = rememberCoroutineScope()

    val onBenefitButtonClick: () -> Unit = {
        scope.launch {
            viewModel.checkLoginStatus()
            if (isLoggedIn) {
                context.startActivity(intent)
            } else {
                showSnackbar("Du måste vara inloggad för att ta del av förmåner")
            }
        }
    }


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

        HTMLContentView2(htmlText = benefit.content ?: "Missing content")

        BenefitButton(onButtonPress = onBenefitButtonClick)
    }
}


@Composable
fun BenefitButton(onButtonPress: () -> Unit) {

    Button(
        onClick = onButtonPress,
        colors = ButtonDefaults.buttonColors(backgroundColor = blue),
        modifier = Modifier.offset(y = (-100).dp)
    ) {
        Text(
            text = "TA DEL AV FÖRMÅN",
            color = Color.White,
            fontSize = 18.sp,
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Preview
@Composable
fun BenefitButtonPreview() {
    BenefitButton {}
}



