package com.levento.sfrapp.feature_user.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levento.sfrapp.R
import com.levento.sfrapp.ui.theme.blue


@Composable
fun LoginScreen(
    onSignInPress: (String, String) -> Unit,
) {

    val focusManager = LocalFocusManager.current
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            
            Image(
                painter = painterResource(id = R.drawable.sfr_logo),
                contentDescription = null,
                modifier = Modifier.padding(top = 30.dp)
            )
            
            TextField(
                value = emailText,
                onValueChange = { emailText = it },
                placeholder = { Text("Email") },
                modifier = Modifier.padding(start = 30.dp, top = 10.dp, end = 30.dp)
            )
            TextField(
                value = passwordText,
                onValueChange = { passwordText = it },
                placeholder = { Text("Lösenord") },
                modifier = Modifier.padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 30.dp)
            )

            Button(
                onClick = {
                    onSignInPress(emailText, passwordText)
                    focusManager.clearFocus()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = blue),
                modifier = Modifier
                    .padding(30.dp)
            ) {
                Text("Logga in", color = Color.White)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onSignInPress = { email, password ->
    })
}