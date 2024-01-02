package hr.foi.air.giveaway.navigation.components


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import hr.foi.air.giveaway.ui.theme.AppTheme

@Composable
fun HomePage() {
    Text(
        text = "Welcome")

}

@Preview
@Composable
fun HomePagePreview() {
    AppTheme {
        HomePage()
    }
}