package com.levento.sfrapp.screens.benefitdetail

import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.levento.sfrapp.domain.Benefit
import com.levento.sfrapp.domain.PlaceHolders
import androidx.compose.runtime.getValue

@Composable
fun BenefitDetailScreen(benefitId: String, viewModel: BenefitDetailViewModel = viewModel()) {

    val benefit by remember { viewModel.benefit }

    LaunchedEffect(benefitId) {
        viewModel.getBenefit(benefitId)
    }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = if (benefit.imageURL == null) {
                painterResource(id = PlaceHolders.categoryImage)
            } else {
                rememberAsyncImagePainter(benefit.imageURL)
            },
            contentDescription = "Benefit image")

        BenefitContent(benefit)
    }
}

@Composable
fun HTMLContentView(htmlText: String) {

    Box(Modifier.padding(20.dp)) {
        val htmlDescription = remember(htmlText) {
            HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }

        AndroidView(
            factory = { context ->
                TextView(context).apply {
                    movementMethod = LinkMovementMethod.getInstance()
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

@Preview
@Composable
fun BenefitDetailScreenPreview() {
    BenefitDetailScreen("ADONnews")
}




val staticHtmlText = "<p>Småföretagarnas Riksförbund och <a href=\\\"https://bit.ly/Skåne_ReLoad\\\" target=\\\"_blank\\\" rel=\\\"noreferrer noopener\\\">Affärskompetens</a> anordnade för första gången en nätverksträff för de företagare som deltar i EU-projektet ReLoad Skåne. I ett sommarhett Törringelund presenteras Sonny Jerlström som driver <a href=\\\"https://www.jerlstromssnickeri.se/\\\" target=\\\"_blank\\\" rel=\\\"noreferrer noopener\\\">speciallösningar inom snickeri</a> och tillika <a href=\\\"https://smaforetagarna.se/forman/medlemskap-online/\\\" target=\\\"_blank\\\" rel=\\\"noreferrer noopener\\\">medlem i Småföretagarnas Riksförbund</a>. Han har under en tid varit i en utvecklingsfas för sitt företag och behövt hantera personal och personalfrågor. </p>\\n\" +\n" +
        "                            \"    \\n\" +\n" +
        "                            \"    \\n\" +\n" +
        "                            \"    \\n\" +\n" +
        "                            \"    <p>Reload är framförallt i första skedet ett sätt för mig att lägga upp en strategi för de fem kommande åren. Målet är att vi ska trippla vår omsättning och bli några till, säger Sonny med stor entusiasm Genom ReLoad Skåne har Sonny fått hjälp av en företagscoach som gett nya metoder och tankar. Ta del av Sonnys engagerade samtal med <a href=\\\"https://smaforetagarna.se/var-agenda/attitydresan/#ingridohlsson\\\" target=\\\"_blank\\\" rel=\\\"noreferrer noopener\\\">Ingrid Ohlsson</a> kring framtidsplaner och företagsutveckling. </p>\\n\" +\n" +
        "                            \"    \\n\" +\n" +
        "                            \"    \\n\" +\n" +
        "                            \"    \\n\" +\n" +
        "                            \"    <p>Är du nyfiken på att själv delta så anmäl dig genom att <a href=\\\"https://bit.ly/SFR_RELOAD\\\" target=\\\"_blank\\\" rel=\\\"noreferrer noopener\\\">KLICKA HÄR</a>.  </p>\\n\" +\n" +
        "                            \"    \\n\" +\n" +
        "                            \"    \\n\" +\n" +
        "                            \"    \\n\" +\n" +
        "                            \"    <p>Ett samarbete mellan <a href=\\\"https://bit.ly/Sk%C3%A5ne_ReLoad\\\" target=\\\"_blank\\\" rel=\\\"noreferrer noopener\\\">Affärskompetens Sverige</a>, <a href=\\\"https://tillvaxtverket.se/\\\" target=\\\"_blank\\\" rel=\\\"noreferrer noopener\\\">Tillväxtverket</a>, <a href=\\\"https://www.slu.se/\\\" target=\\\"_blank\\\" rel=\\\"noreferrer noopener\\\">SLU</a> och Småföretagarnas Riksförbund.</p>\\n\" +\n" +
        "                            \"    \\n\" +\n" +
        "                            \"    \\n\" +\n" +
        "                            \"    \\n\" +\n" +
        "                            \"    <figure class=\\\"wp-block-embed is-type-video is-provider-youtube wp-block-embed-youtube wp-embed-aspect-16-9 wp-has-aspect-ratio\\\"><div class=\\\"wp-block-embed__wrapper\\\">\\n\" +\n" +
        "                            \"    <div class=\\\"ast-oembed-container\\\"><iframe loading=\\\"lazy\\\" title=\\\"Jerlströms Snickeri växlar upp sitt företagande\\\" width=\\\"1200\\\" height=\\\"675\\\" src=\\\"https://www.youtube.com/embed/WKJz0Ihm3BI?feature=oembed\\\" frameborder=\\\"0\\\" allow=\\\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\\\" allowfullscreen></iframe></div>\\n\" +\n" +
        "                            \"    </div></figure>"