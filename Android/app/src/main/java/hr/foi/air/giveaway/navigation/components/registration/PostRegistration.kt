package hr.foi.air.giveaway.navigation.components.registration

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.foi.air.giveaway.ui.components.StyledButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostRegistration(
    newUsername: String,
    onNoticeUnderstood: () -> Unit
) {
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
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = buildString {
                    append("You have successfully registered with username '$newUsername'. ")
                },
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(vertical = 50.dp)
            )

            StyledButton(label = "I understand", onClick = onNoticeUnderstood)
        }
    }
}

@Preview
@Composable
fun PostRegistrationPreview() {
    PostRegistration(newUsername = "nbiskup", onNoticeUnderstood = {})
}