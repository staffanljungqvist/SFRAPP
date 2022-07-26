package com.levento.sfrapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.levento.sfrapp.MainScreen
import com.levento.sfrapp.R
import com.levento.sfrapp.ui.theme.SFRAPPTheme

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SFRAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val onClose = { finish() }
                    CardScreen(onClose)
                }
            }
        }
    }
}

@Composable
fun CardScreen(onClose: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxHeight()) {
            Text("Förmånskort")
            Text("Stäng", modifier = Modifier.clickable(onClick = onClose))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CardScreenPreview() {
    CardScreen({})
}