package hr.foi.air.giveaway.navigation.components


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import hr.foi.air.giveaway.ui.theme.AppTheme
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(purchased: Boolean, onReturnToStoreClick: () -> Unit) {
    var feedback by remember { mutableStateOf("") }

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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (purchased) "Thank you for your purchase!" else "Welcome!",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = feedback,
                onValueChange = { feedback = it },
                label = { Text(if (purchased) "Leave your feedback" else "Leave a comment") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            )

            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
            ) {
                Text(if (purchased) "Submit Feedback" else "Submit Comment")
            }

            Button(
                onClick = { onReturnToStoreClick() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
            ) {
                Text("Go to Store")
            }
        }
    }
}

@Preview
@Composable
fun HomePagePreview() {
    AppTheme {
        HomePage(false, onReturnToStoreClick = {})
    }
}

@Preview
@Composable
fun HomePagePurchasePreview() {
    AppTheme {
        HomePage(true, onReturnToStoreClick = {})
    }
}