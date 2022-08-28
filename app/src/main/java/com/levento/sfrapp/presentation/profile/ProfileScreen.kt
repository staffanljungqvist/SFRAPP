package com.levento.sfrapp.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.levento.sfrapp.domain.model.AuthResponse
import com.levento.sfrapp.domain.model.User
import kotlinx.coroutines.launch


@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    isLoggedIn: Boolean,
    user: User,
    checkLoginStatus: () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()

    val onSignInAttempt: (String, String) -> Unit = { email, password ->
        coroutineScope.launch() {
            viewModel.login(email, password)
            checkLoginStatus()
        }
    }

    val logOut: () -> Unit = {
        coroutineScope.launch {
            viewModel.logOut()
            checkLoginStatus()
        }
    }

    if (isLoggedIn) {
        UserInfoScreen(user = user, logOut)
    } else {
        LoginScreen(onSignInPress = onSignInAttempt)

        when(val response = viewModel.signInResponse) {
            is AuthResponse.Loading -> Box(
                modifier = Modifier
                    .fillMaxSize().padding(50.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                CircularProgressIndicator()
            }
            is AuthResponse.Success -> {
                if (response.data) {
                    //  navigateToProfileScreen()
                }
            }
            is AuthResponse.Error -> {

            }
        }

    }





}