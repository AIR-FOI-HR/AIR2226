package hr.foi.air.giveaway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.foi.air.giveaway.navigation.components.EntryPage
import hr.foi.air.giveaway.navigation.components.HomePage
import hr.foi.air.giveaway.navigation.components.login.LoginPage
import hr.foi.air.giveaway.navigation.components.payment.CartPage
import hr.foi.air.giveaway.navigation.components.products.ProductsPage
import hr.foi.air.giveaway.navigation.components.registration.PostRegistration
import hr.foi.air.giveaway.navigation.components.registration.RegistrationPage
import hr.foi.air.giveaway.ui.theme.AppTheme
import hr.foi.air.mail_login.MailLoginHandler
import hr.foi.air.standard_auth_login.StandardAuthLoginHandler

class MainActivity : ComponentActivity() {
    private val loginHandlers = listOf(StandardAuthLoginHandler(), MailLoginHandler())
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
                            LoginPage(
                                onSuccessfulLogin = {
                                    // navController.navigate("home")
                                    navController.navigate("products")
                                },
                                onFailedLogin = {
                                },
                                loginHandlers = loginHandlers
                            )
                        }
                        composable("register") {
                            RegistrationPage(
                                onSuccessfulRegistration = { newUsername ->
                                    navController.navigate("register/$newUsername/notice")
                                }
                            )
                        }
                        composable(
                            "register/{username}/notice",
                            arguments = listOf(navArgument("username") { type = NavType.StringType })
                        ) { backStackEntry ->
                            PostRegistration(
                                newUsername = backStackEntry.arguments?.getString("username") ?: "?",
                                onNoticeUnderstood = {
                                    repeat(2) { navController.popBackStack() }
                                }
                            )
                        }
                        composable("home") {
                            HomePage()
                        }
                        composable("products") {
                            ProductsPage(
                                onCartButtonClick = {
                                    navController.navigate("cart")
                                }
                            )
                        }
                        composable("cart") {
                            CartPage()
                        }
                    }
                }
            }
        }
    }
}