package com.levento.sfrapp.feature_info.presentation.info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.levento.sfrapp.R

@Composable
fun InfoScreen(navController: NavController, viewModel: InfoViewModel = hiltViewModel()) {

    val onClick: (String) -> Unit = { route ->
        navController.navigate(route)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 62.dp, top = 62.dp)
    ) {
        InfoItem(icon = R.drawable.ic_baseline_groups_24, text = "Om oss", onClick, "aboutUs")
        InfoItem(icon = R.drawable.ic_baseline_email_24, text = "Kontakta oss", onClick, "contact")
        InfoItem(icon = R.drawable.ic_baseline_lock_24, text = "GDPR", onClick, "gdpr")
    }
}

@Composable
fun InfoItem(icon: Int, text: String, onClick: (String) -> Unit, route: String) {
    Row(verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick(route) }
    ) {
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
    InfoItem(icon = R.drawable.ic_baseline_groups_24, text = "Om oss", onClick = {}, "")
}

@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    val context = LocalContext.current
    InfoScreen(NavController(context))
}