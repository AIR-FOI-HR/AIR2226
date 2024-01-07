package hr.foi.air.giveaway.navigation.components.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.foi.air.core.login.LoginHandler
import hr.foi.air.core.login.LoginOutcomeListener
import hr.foi.air.giveaway.ui.components.PasswordTextField
import hr.foi.air.giveaway.ui.components.StyledButton
import hr.foi.air.giveaway.ui.components.StyledTextField
import hr.foi.air.standard_auth_login.StandardAuthLoginHandler
import hr.foi.air.standard_auth_login.StandardAuthLoginToken
import hr.foi.air.google_login.GoogleLoginHandler
import hr.foi.air.google_login.GoogleLoginToken

@Composable
fun LoginPage (
    onSuccessfulLogin: () -> Unit,
    loginHandler: LoginHandler
) {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var errorMessage by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        if (errorMessage == "") {
            Text(
                "Login"
            )
        } else {
            Text(
                text = errorMessage,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        StyledTextField(label = "Username", value = username, onValueChange = { username = it })
        PasswordTextField(
            label = "Password",
            value = password,
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(50.dp))

        StyledButton(
            label = "Login",
            onClick = {
                val standardAuthLoginToken = StandardAuthLoginToken(username, password)
                val googleLoginToken = GoogleLoginToken(username, password)

                loginHandler.handleLogin(googleLoginToken, object : LoginOutcomeListener {
                    override fun onSuccessfulLogin(username: String) {
                        onSuccessfulLogin()
                    }
                    override fun onFailedLogin(reason: String) {
                        errorMessage = reason
                    }
                })
            }
        )
    }
}

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage({}, GoogleLoginHandler())
}