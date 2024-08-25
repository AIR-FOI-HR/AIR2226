package hr.foi.air.giveaway.navigation.components.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginPage (
    viewModel: LoginViewModel = viewModel(),
    onSuccessfulLogin: () -> Unit,
    onFailedLogin: () -> Unit,
    loginHandlers: List<LoginHandler>
) {
    val identifier = viewModel.identifier.observeAsState().value ?: ""
    val password = viewModel.password.observeAsState().value ?: ""

    var selectedHandler by remember { mutableStateOf<LoginHandler?>(null) }

    Scaffold(
        topBar = {
            Surface(color = MaterialTheme.colors.primaryVariant, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "GiveAway",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {

            loginHandlers.forEach { handler ->
                StyledButton(
                    label = handler.getLabel(),
                    onClick = {
                        selectedHandler = if (selectedHandler == handler) null else handler
                    }
                )

                if (selectedHandler == handler) {
                    Spacer(modifier = Modifier.height(16.dp))

                    StyledTextField(
                        label = "identifier",
                        value = identifier,
                        onValueChange = { viewModel.identifier.value = it })

                    PasswordTextField(
                        label = "Password",
                        value = password,
                        onValueChange = { viewModel.password.value = it },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    Button(
                        onClick = {
                            val token = handler.generateLoginToken(identifier, password)
                            viewModel.login(
                                handler,
                                token,
                                onSuccessfulLogin,
                                onFailedLogin
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant),
                        enabled = true
                    ) {
                        Text("Login")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
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