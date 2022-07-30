package com.levento.sfrapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.R

@Composable
fun InfoScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        InfoMeny()
    }
}

@Composable
fun InfoMeny() {
    Column(modifier = Modifier.padding(64.dp)) {
        InfoItem(icon = R.drawable.ic_baseline_groups_24, text = "Om oss")
        InfoItem(icon = R.drawable.ic_baseline_email_24, text = "Kontakta oss")
        InfoItem(icon = R.drawable.ic_baseline_lock_24, text = "GDPR")
    }
}

@Composable
fun InfoItem(icon: Int, text: String) {
    Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(8.dp)) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text // decorative element
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.body1)
    }
}

@Preview
@Composable
fun InfoItemPreview() {
    InfoItem(icon = R.drawable.ic_baseline_groups_24, text = "Om oss")
}

@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    InfoScreen()
}