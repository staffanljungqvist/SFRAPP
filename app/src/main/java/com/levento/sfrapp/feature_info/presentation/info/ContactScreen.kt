package com.levento.sfrapp.screens.info

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import com.levento.sfrapp.R

@Composable
fun ContactScreen() {
    Column(
        Modifier
            .padding(40.dp)
            .fillMaxSize()) {
        ContactPart("Allmänna frågor", "info@smaforetagarna.se", phone = "042-32 28 50")
        ContactPart("Juridisk rådgivning", "info@smaforetagarna.se", phone = "042-32 28 50")

    }
}

@Composable
fun ContactPart(title: String, mail: String, phone: String) {



    val style = MaterialTheme.typography.h5

    Column() {
        Text(
            text = title,
            style = style,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Row() {
            Text(text = "E-post:    ", style = style)
            Text(text = mail, style = style)
        }
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            Text(text = "Telefon:    ", style = style)
            Text(text = phone, style = style)
        }

        PhoneHours(style = style)

        Spacer(Modifier.height(50.dp))
    }
}

@Composable
fun PhoneHours(style: TextStyle) {

    var shouldShowTimes by remember { mutableStateOf(false) }


    
    val isRotated = remember { mutableStateOf(false) }

    val showTimes: () -> Unit = {
        isRotated.value = !isRotated.value

    }



    val angle: Float by animateFloatAsState(
        targetValue = if (isRotated.value) 90F else 0F,
        animationSpec = tween(
            durationMillis = 300, // duration
            easing = FastOutSlowInEasing
        ),
        finishedListener = {
            // disable the button
         //   isEnabled.value = true
            shouldShowTimes = !shouldShowTimes
        }
    )

    Row() {
        Text("Telefontid:", style = style)
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_right_24),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    showTimes()
                }
                .rotate(angle)
        )

        Spacer(Modifier.weight(1f))

        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_phone_24),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_email_24),
            contentDescription = null
        )
    }

    if (shouldShowTimes) {
        Row() {
            PhoneHourText(style = style)
        }
    }
    

}

@Composable
fun PhoneHourText(style: TextStyle) {

    Column() {
        Text(text = "Måndag, tisdag, torsdag: 8.00-16.00\n" +
                "Onsdag 8.00 – 17.00\n" +
                "Fredag 8.00 – 12.00\n" +
                "(Chatten öppnar 07.30)\n" +
                "Stängt för lunch 12.00-13.00",
            style = style
        )
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactScreenPreview() {
    SFRAPPTheme {
        ContactScreen()
    }
}