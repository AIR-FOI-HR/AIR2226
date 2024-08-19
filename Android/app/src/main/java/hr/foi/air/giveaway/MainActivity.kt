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
import hr.foi.air.giveaway.mockdataproduct.ProductRepository
import hr.foi.air.giveaway.mockdataproduct.Product
import hr.foi.air.giveaway.navigation.components.EntryPage
import hr.foi.air.giveaway.navigation.components.HomePage
import hr.foi.air.giveaway.navigation.components.login.LoginPage
import hr.foi.air.giveaway.navigation.components.payment.CartPage
import hr.foi.air.giveaway.navigation.components.payment.PaymentPage
import hr.foi.air.giveaway.navigation.components.payment.PaymentProcessingPage
import hr.foi.air.giveaway.navigation.components.products.ProductDetails
import hr.foi.air.giveaway.navigation.components.products.ProductsPage
import hr.foi.air.giveaway.navigation.components.registration.PostRegistration
import hr.foi.air.giveaway.navigation.components.registration.RegistrationPage
import hr.foi.air.giveaway.ui.theme.AppTheme
import hr.foi.air.mail_login.MailLoginHandler
import hr.foi.air.standard_auth_login.StandardAuthLoginHandler
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val loginHandlers = listOf(StandardAuthLoginHandler(), MailLoginHandler())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            ProductRepository.generateMockProducts(applicationContext)
        }

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
                                    navController.navigate("home/false")
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
                        composable("home/{purchased}",
                            arguments = listOf(navArgument("purchased") { type = NavType.BoolType })
                        ) { backStackEntry ->
                            val purchased = backStackEntry.arguments?.getBoolean("purchased") ?: false
                            HomePage(
                                purchased = purchased,
                                onReturnToStoreClick = {
                                    navController.navigate("products")
                                }
                            )
                        }
                        composable("products") {
                            ProductsPage(
                                onProductClick = { clickedProduct ->
                                    navController.navigate("productDetails/${clickedProduct.id}")
                                },
                                onCartButtonClick = {
                                    navController.navigate("cart")
                                }
                            )
                        }
                        composable("productDetails/{clickedProduct.id}") { backStackEntry ->
                            val productId = backStackEntry.arguments?.getString("clickedProduct.id")?.toIntOrNull()
                            val context = LocalContext.current
                            val productState = remember { mutableStateOf<Product?>(null) }

                            LaunchedEffect(productId) {
                                if (productId != null) {
                                    val product = ProductRepository.getProductById(context, productId)
                                    productState.value = product
                                }
                            }

                            productState.value?.let { product ->
                                ProductDetails(product = product)
                            }
                        }
                        composable("cart") {
                            CartPage(
                                onPurchaseClick = { totalCartPrice ->
                                    navController.navigate("payment/$totalCartPrice")
                                }
                            )
                        }
                        composable("payment/{totalCartPrice}"){backStackEntry ->
                            val totalCartPrice = backStackEntry.arguments?.getString("totalCartPrice")?.toDoubleOrNull() ?: 0.0
                            PaymentPage(
                                onReturnToStoreClick = {
                                    navController.navigate("products")
                                },
                                totalCartPrice = totalCartPrice,
                                onProceedToPayment = {
                                    navController.navigate("paymentProcessing/$totalCartPrice")
                                })
                        }
                        composable("paymentProcessing/{totalCartPrice}") { backStackEntry ->
                            val totalCartPrice = backStackEntry.arguments?.getString("totalCartPrice")?.toDoubleOrNull() ?: 0.0
                            PaymentProcessingPage(
                                totalCartPrice = totalCartPrice,
                                onPaymentSuccess = {
                                    navController.navigate("home/true")
                                },
                                onCancelPayment = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}