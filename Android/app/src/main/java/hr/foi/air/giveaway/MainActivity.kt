package hr.foi.air.giveaway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hr.foi.air.giveaway.navigation.components.EntryPage
import hr.foi.air.giveaway.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "entry") {
                        composable("entry") {
                            EntryPage(
                                onLoginButtonClick = {
                                    navController.navigate("login")
                                },
                                onRegistrationButtonClick = {
                                    navController.navigate("register")
                                }
                            )
                        }
                        composable("login") {

                        }
                        composable("register") {

                        }
                    }
                }

            }

        }


    }
}