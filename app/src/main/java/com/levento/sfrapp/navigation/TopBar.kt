package com.levento.sfrapp.navigation

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun TopBar() {
    TopAppBar(title = {
        Text("SFR")
    })
}