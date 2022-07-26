package com.levento.sfrapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.domain.PlaceHolders
import com.levento.sfrapp.domain.UserCompany

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
    Text(text = header, fontWeight = FontWeight.Bold)
    Text(text = text, modifier = Modifier.padding(bottom = 8.dp))
}

@Composable
fun UserScreenPreview() {
    ProfileScreen(company = PlaceHolders.userCompany)
}