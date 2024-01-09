package hr.foi.air.giveaway.navigation.components.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.foi.air.core.login.LoginHandler
import hr.foi.air.giveaway.ui.components.PasswordTextField
import hr.foi.air.giveaway.ui.components.StyledButton
import hr.foi.air.giveaway.ui.components.StyledTextField
import hr.foi.air.giveaway.viewmodels.LoginViewModel
import hr.foi.air.mail_login.MailLoginHandler
import hr.foi.air.standard_auth_login.StandardAuthLoginHandler
import hr.foi.air.standard_auth_login.StandardAuthLoginToken
import hr.foi.air.mail_login.MailLoginToken

@Composable
fun LoginPage (
    viewModel: LoginViewModel = viewModel(),
    onSuccessfulLogin: () -> Unit,
    onFailedLogin: () -> Unit,
    loginHandlers: List<LoginHandler>
) {
    val identifier = viewModel.identifier.observeAsState().value ?: ""
    val password = viewModel.password.observeAsState().value ?: ""

    var awaitingResponse by remember { mutableStateOf(false) }
    val errorMessage = viewModel.errorMessage.observeAsState().value ?: ""

    var handler by remember { mutableStateOf(0) }
    val currentLoginHandler = loginHandlers[handler]

    val newLabel = when(handler) {
        0 -> "username"
        1 -> "mail"
        else ->"identifier"
    }

    val newText = when(handler) {
        0 -> "Login with username and password"
        1 -> "Login with email and password"
        else -> "Login with some identifier"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        if (errorMessage == "") {
            Text(
                newText
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

        StyledTextField(
            label = newLabel,
            value = identifier,
            onValueChange = { viewModel.identifier.value = it })
        PasswordTextField(
            label = "Password",
            value = password,
            onValueChange = { viewModel.password.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(50.dp))

        StyledButton(
            label = "Login",
            enabled = !awaitingResponse,
            onClick = {
                val currentLoginToken = when(handler) {
                    0 -> StandardAuthLoginToken(identifier, password)
                    1 -> MailLoginToken(identifier, password)
                    else -> StandardAuthLoginToken(identifier, password)
                }

                awaitingResponse = true
                viewModel.login(
                    currentLoginHandler,
                    currentLoginToken,
                    onSuccessfulLogin = {
                        awaitingResponse = false
                        onSuccessfulLogin()
                    },
                    onFailedLogin = {
                        handler++
                        if(handler >= loginHandlers.size) {
                            handler = 0
                        }
                        awaitingResponse = false
                        onFailedLogin()
                    }
                )
            }
        )
    }
}

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage(
        onSuccessfulLogin = {},
        onFailedLogin = {},
        loginHandlers = listOf(StandardAuthLoginHandler(), MailLoginHandler())
    )
}