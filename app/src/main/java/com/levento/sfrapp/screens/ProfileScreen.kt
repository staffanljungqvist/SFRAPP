package com.levento.sfrapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.data.PlaceHolders
import com.levento.sfrapp.models.User
import com.levento.sfrapp.ui.theme.blue


@Composable
fun ProfileScreen(user: User, onLogout: () -> Unit) {
    
    Column(Modifier.fillMaxSize().padding(start = 32.dp, top = 32.dp)) {
        UserInfoList(user = user)

        Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Logga ut", modifier = Modifier.clickable { onLogout() }, color = blue)

    }
    
}

@Composable
fun UserInfoList(user: User, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        ProfileInfoText(header = "Medlemsnummer", text = user.memberId ?: "Missing data")
        ProfileInfoText(header = "Företagsnamn", text = user.companyName ?: "Missing data")
        ProfileInfoText(header = "Org.nr", text = user.orgNr ?: "Missing data")
        ProfileInfoText(header = "Ort", text = user.locality ?: "Missing data")
        ProfileInfoText(header = "Address", text = user.address ?: "Missing data")
        ProfileInfoText(header = "Tel.nr", text = user.phone ?: "Missing data")
        ProfileInfoText(header = "Kontaktperson", text = user.contactPerson ?: "Missing data")
        ProfileInfoText(header = "Medlemskap giltig t.om.", text = user.validThru ?: "Missing data")
    }
}

@Composable
fun ProfileInfoText(header: String, text: String) {
    Text(text = header, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.body1)
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    ProfileScreen(user = PlaceHolders.userCompany, onLogout = {})
}