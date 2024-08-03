package hr.foi.air.giveaway.navigation.components.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.foi.air.giveaway.ui.theme.AppTheme
import hr.foi.air.giveaway.viewmodels.CartViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.foi.air.giveaway.mockdataproduct.Product
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CartPage(
    onPurchaseClick: (Double) -> Unit,
    viewModel: CartViewModel = viewModel()
) {
    val removeFromCart: (Product) -> Unit = { product ->
        viewModel.removeFromCart(product)
    }

    val increaseQuantity: (Product) -> Unit = { product ->
        viewModel.increaseQuantity(product)
    }

    val decreaseQuantity: (Product) -> Unit = { product ->
        viewModel.decreaseQuantity(product)
    }

    val totalCartPrice = viewModel.cardItems.value.sumByDouble { it.price * it.productQuantity }

    LazyColumn{
        items(viewModel.cardItems.value) {
            product -> CartItemCard(
                product = product,
                removeFromCart = removeFromCart,
                increaseQuantity = increaseQuantity,
                decreaseQuantity = decreaseQuantity
            )
        }
        item {
            Text(
                text = "Total: $totalCartPrice",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Start
            )
        }
        item {
            Button(
                onClick = { onPurchaseClick(totalCartPrice) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
            ) {
                Text("Purchase", color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun CartPagePreview() {
    AppTheme {
        CartPage({})
    }
}

@Composable
fun CartItemCard(
    product: Product,
    removeFromCart: (Product) -> Unit,
    increaseQuantity: (Product) -> Unit,
    decreaseQuantity: (Product) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(160.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Product: ${product.name}")
            Text("Price: ${product.price}")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Quantity: ${product.productQuantity}",
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = { increaseQuantity(product) },
                    modifier = Modifier.size(30.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Increase Quantity"
                    )
                }

                IconButton(
                    onClick = { decreaseQuantity(product) },
                    modifier = Modifier.size(30.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Decrease Quantity"
                    )
                }
            }
            Button(
                onClick = { removeFromCart(product) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
            ) {
                Text("Remove from Cart", color = Color.White)
            }
        }
    }
}