package hr.foi.air.giveaway.navigation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.foi.air.giveaway.ui.theme.Shapes

@Composable
fun EntryPage(
    onLoginButtonClick: () -> Unit,
    onRegistrationButtonClick: () -> Unit,
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
        },
        bottomBar = {
            Text(
                text = "Copyright FOI 2023",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                text = "Welcome to GiveAway",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Button(
                onClick = onLoginButtonClick,
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .defaultMinSize(minWidth = 80.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
            ) {
                Text("Login", color = Color.White)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Divider(modifier = Modifier.fillMaxWidth(0.47f), color = Color.LightGray, thickness = 2.dp)
                Text("OR")
                Divider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 2.dp)
            }

            Button(
                onClick = onRegistrationButtonClick,
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .defaultMinSize(minWidth = 80.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
                shape = Shapes.large
            ) {
                Text("Register", color = Color.White)
            }
       }
    }
}

@Preview
@Composable
fun EntryPagePreview() {
    EntryPage({}, {})
}
