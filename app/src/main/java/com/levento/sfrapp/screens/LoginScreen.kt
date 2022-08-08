package com.levento.sfrapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier.padding(8.dp),
    onLoginButtonClick: (String?, String?) -> Unit
) {

    val focusManager = LocalFocusManager.current
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            TextField(
                value = emailText,
                onValueChange = { emailText = it },
                placeholder = { Text("Email") },
                modifier = modifier
            )
            TextField(
                value = passwordText,
                onValueChange = { passwordText = it },
                placeholder = { Text("Password") },
                modifier = modifier
            )

            Button(
                onClick = {
                    onLoginButtonClick(emailText, passwordText)
                    focusManager.clearFocus()
                },
                modifier = modifier
            ) {
                Text("Logga in")
            }

            Spacer(modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
                Text("Already a member?")
                Text("Login", modifier.clickable { }, color = Color.Blue)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginButtonClick = { email, password ->
    })
}