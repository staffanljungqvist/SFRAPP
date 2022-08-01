package com.levento.sfrapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.models.PlaceHolders
import com.levento.sfrapp.models.UserCompany

@Composable
fun ProfileScreen(company: UserCompany) {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(64.dp)) {
            ProfileInfoText(header = "Medlemsnummer", text = company.memberId)
            ProfileInfoText(header = "Företagsnamn", text = company.companyName)
            ProfileInfoText(header = "Org.nr", text = company.orgNr)
            ProfileInfoText(header = "Ort", text = company.locality)
            ProfileInfoText(header = "Address", text = company.address)
            ProfileInfoText(header = "Tel.nr", text = company.phone)
            ProfileInfoText(header = "Kontaktperson", text = company.contact)
            ProfileInfoText(header = "Medlemskap giltig t.om.", text = company.validThru)
        }
    }
}

@Composable
fun ProfileInfoText(header: String, text: String) {
    Text(text = header, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.body1)
    Text(text = text, style = MaterialTheme.typography.body1, modifier = Modifier.padding(bottom = 8.dp))
}

@Preview
@Composable
fun UserScreenPreview() {
    ProfileScreen(company = PlaceHolders.userCompany)
}