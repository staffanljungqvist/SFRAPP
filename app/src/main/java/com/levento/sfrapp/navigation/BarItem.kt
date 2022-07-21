package com.levento.sfrapp.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String,
    val drawable: Int? = null
)