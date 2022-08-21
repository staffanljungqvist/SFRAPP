package com.levento.sfrapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.R
import com.levento.sfrapp.ui.theme.dark

@Composable
fun TopBar(
    currentRoute: String?
) {

    val pageName = when (currentRoute) {
        "home" -> "Hem"
        "benefits" -> "Förmåner"
        "profile" -> "Min Sida"
        "info" -> "Information"
        "articleDetail" -> "Artikel"
        "benefitDetail" -> "Förmån"
        "aboutUs" -> "Om Oss"
        "contact" -> "Kontakta Oss"
        "gdpr" -> "GDPR"
        else -> ""
    }

    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        TopAppBar(
            title = {
                Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalAlignment = Alignment.End) {
                    Text(pageName, color = Color.White, style = MaterialTheme.typography.subtitle2)

                }
            },
            backgroundColor = dark,
        )

/*        Box(contentAlignment = Alignment.Center, modifier = Modifier.offset(y = 20.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.bee),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(55.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.bee),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }*/
    }
}


@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFF622D38)
@Composable
fun TopBarPreview() {
    TopBar("home")
}