package com.levento.sfrapp.presentation.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.R
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import com.levento.sfrapp.ui.theme.red

@Composable
fun AboutScreen(modifier: Modifier = Modifier.padding(bottom = 20.dp)) {

    val scrollState = rememberScrollState()

    Column(Modifier.verticalScroll(scrollState)) {

        Image(
            painter = painterResource(id = R.drawable.header2),
            contentDescription = ""
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)

        ) {


            Headline(text = R.string.about_headline1, modifier = modifier)
            Paragraph(text = R.string.about_paragraf1, modifier = modifier)
            Headline(text = R.string.about_headline2, modifier = modifier)
            Paragraph(text = R.string.about_paragraf2, modifier = modifier)
            Headline(text = R.string.about_headline3, modifier = modifier)
            Paragraph(text = R.string.about_paragraf3, modifier = modifier)
            Headline(text = R.string.about_headline4, modifier = modifier)
            Paragraph(text = R.string.about_paragraf4, modifier = modifier)

        }
    }
}

@Composable
fun Headline(text: Int, modifier: Modifier = Modifier) {
    Text(
        stringResource(
            id = text
        ),
        style = MaterialTheme.typography.h1,
        color = red,
        modifier = modifier
    )
}

@Composable
fun Paragraph(text: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = text),
        style = MaterialTheme.typography.h4,
        modifier = modifier.padding(bottom = 20.dp)
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview() {
    SFRAPPTheme() {
        AboutScreen()
    }
}