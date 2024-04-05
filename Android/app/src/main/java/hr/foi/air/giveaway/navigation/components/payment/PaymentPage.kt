package hr.foi.air.giveaway.navigation.components.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.foi.air.giveaway.ui.theme.AppTheme
import hr.foi.air.giveaway.viewmodels.CartViewModel
import androidx.compose.ui.text.font.FontWeight


@Composable
fun PaymentPage(
    onReturnToStoreClick: () -> Unit,
    totalCartPrice: Double,
    viewModel: CartViewModel = viewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Product", fontWeight = FontWeight.Bold)
            Text(
                "Quantity",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text("Price", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 16.dp))
        }

        Divider(color = Color.Black, thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))

        viewModel.cardItems.value.forEach { product ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(product.name)
                Text(
                    text = product.productQuantity.toString(),
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = "${product.price * product.productQuantity}",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("*Delivery")
            Text(
                text = "---",
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("*Tax")
            Text(
                text = "---",
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Divider(color = Color.Black, thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text("Total Price:", fontWeight = FontWeight.Bold)
            Text("$totalCartPrice", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 16.dp))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Total Price: $totalCartPrice",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
        ) {
            Text("Complete Purchase", color = Color.White)
        }
        Button(
            onClick = { onReturnToStoreClick() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant)
        ) {
            Text("Return to Store", color = Color.White)
        }
    }

}

@Preview
@Composable
fun PaymentPagePreview() {
    AppTheme {
        PaymentPage({},0.00)
    }
}