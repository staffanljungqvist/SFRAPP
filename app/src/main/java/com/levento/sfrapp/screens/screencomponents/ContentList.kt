package com.levento.sfrapp.screens.screencomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.ui.theme.red

@Composable
fun ContentList(header: String, content: @Composable () -> Unit) {
    Column() {
        Text(
            text = header.uppercase(),
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
        )
        content()
        Spacer(modifier = Modifier.padding(bottom = 28.dp))
    }
}