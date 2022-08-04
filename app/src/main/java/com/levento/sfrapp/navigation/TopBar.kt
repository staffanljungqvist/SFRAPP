package com.levento.sfrapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
        "profile" -> "Din sida"
        "info" -> "Information"
        else -> ""
    }


    Box(
        contentAlignment = Alignment.TopCenter
    ) {

        TopAppBar(
            title = {
                Text(pageName, color = Color.White)
            },
            backgroundColor = dark,
            modifier = Modifier.padding(top = 30.dp),
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.bee),
                    contentDescription = null,
                    modifier = Modifier.padding(5.dp)
                )
            }
        )

    }
}


@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFF622D38)
@Composable
fun TopBarPreview() {
    TopBar("Hem")
}