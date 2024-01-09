package hr.foi.air.giveaway.navigation.components.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.foi.air.giveaway.ui.components.StyledButton

@Composable
fun PostRegistration(
    newUsername: String,
    onNoticeUnderstood: () -> Unit
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

@Preview
@Composable
fun PostRegistrationPreview() {
    PostRegistration(newUsername = "nbiskup", onNoticeUnderstood = {})
}