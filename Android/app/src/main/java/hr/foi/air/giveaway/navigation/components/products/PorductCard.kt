package hr.foi.air.giveaway.navigation.components.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.foi.air.giveaway.mockdataprodutc.MockProducts
import hr.foi.air.giveaway.mockdataprodutc.Product
import hr.foi.air.giveaway.ui.theme.AppTheme

@Composable
fun ProductCard(product: Product, onCartButtonClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
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
            Text(
                text = product.name,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = product.description,
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.primaryVariant
            )
            Button(
                onClick = onCartButtonClick,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
            ) {
                Text("Add to Cart", color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun ProductsCardPreview() {
    AppTheme {
        ProductCard(MockProducts.generateMockProducts().first(), {})
    }
}