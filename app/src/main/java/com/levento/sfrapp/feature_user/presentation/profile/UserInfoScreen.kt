package com.levento.sfrapp.feature_user.presentation.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.feature_user.domain.model.User
import com.levento.sfrapp.ui.theme.blue

@Composable
fun UserInfoScreen(user: User, onLogOut: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 62.dp, top = 62.dp)) {
        UserInfoText(header = "Medlemsnummer", text = user.memberId ?: "Missing data")
        UserInfoText(header = "Företagsnamn", text = user.companyName ?: "Missing data")
        UserInfoText(header = "Org.nr", text = user.orgNr ?: "Missing data")
        UserInfoText(header = "Ort", text = user.locality ?: "Missing data")
        UserInfoText(header = "Address", text = user.address ?: "Missing data")
        UserInfoText(header = "Tel.nr", text = user.phone ?: "Missing data")
        UserInfoText(header = "Kontaktperson", text = user.contactPerson ?: "Missing data")
        UserInfoText(header = "Medlemskap giltig t.om.", text = user.expirationDate ?: "Missing data")

        Spacer(Modifier.height(50.dp))

        Text(
            text = "Logga ut", modifier = Modifier
                .clickable { onLogOut() },
            color = blue
        )
    }
}

@Composable
fun UserInfoText(header: String, text: String) {
    Text(text = header, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.body1)
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}