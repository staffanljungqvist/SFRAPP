package com.levento.sfrapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.ui.theme.dark
import com.levento.sfrapp.R
import com.levento.sfrapp.ui.theme.backgroundColor

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


    Column(
        modifier = Modifier
            .height(100.dp)
            .background(backgroundColor)
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
        ) {
            Column() {
                TopAppBar(
                    title = {
                        Text(pageName, color = Color.White)
                    },
                    backgroundColor = dark
                )

                Spacer(Modifier.height(40.dp))
            }

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                //    .background(backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bee),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TopBarPreview() {
    TopBar("Hem")

}