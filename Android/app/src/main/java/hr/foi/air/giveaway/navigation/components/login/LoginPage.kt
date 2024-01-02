package hr.foi.air.giveaway.navigation.components.login

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
import hr.foi.air.giveaway.entities.MockDataLoader
import hr.foi.air.giveaway.entities.RegistrationBody
import hr.foi.air.giveaway.ui.components.PasswordTextField
import hr.foi.air.giveaway.ui.components.StyledButton
import hr.foi.air.giveaway.ui.components.StyledTextField
@Composable
fun LoginPage (
    onSuccessfulLogin: () -> Unit
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
                val registrationUser: RegistrationBody? = MockDataLoader.getDataByUsername(username)

                if (registrationUser != null && username == registrationUser.username && password == registrationUser.password) {
                    onSuccessfulLogin()
                } else {
                    errorMessage = "Wrong mock credentials entered!"
                }
            }
        )
    }
}

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage({})
}