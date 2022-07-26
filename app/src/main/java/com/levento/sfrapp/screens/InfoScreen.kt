package com.levento.sfrapp.screens

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.domain.PlaceHolders
import com.levento.sfrapp.domain.UserCompany

@Composable
fun InfoScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Text("Info")
    }
}

@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    InfoScreen()
}