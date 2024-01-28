package hr.foi.air.giveaway.navigation.components.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.foi.air.giveaway.ui.theme.AppTheme
import hr.foi.air.giveaway.viewmodels.ProductsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.foi.air.giveaway.mockdataprodutc.ProductType



@Composable
fun ProductsPage(
    onCartButtonClick: () -> Unit,
    viewModel: ProductsViewModel = viewModel()
) {
    var expanded  by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "GiveAway",
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                },
                actions = {
                    IconButton(
                        onClick = onCartButtonClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Shopping Cart"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                elevation = AppBarDefaults.TopAppBarElevation
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            if (!expanded) {
                Button(
                    onClick = {
                        expanded = !expanded
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
                ) {
                    Text("Filters")
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = viewModel.minPriceFilter.value.toString(),
                        onValueChange = { newValue ->
                            viewModel.minPriceFilter.value = newValue.toDoubleOrNull() ?: 0.0
                        },
                        label = { Text("Min Price") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp)
                    )
                    OutlinedTextField(
                        value = viewModel.maxPriceFilter.value.toString(),
                        onValueChange = { newValue ->
                            viewModel.maxPriceFilter.value = newValue.toDoubleOrNull() ?: 0.0
                        },
                        label = { Text("Max Price") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp)
                    )
                }

                LazyRow {
                    items(ProductType.values()) { productType ->
                        Row(
                            modifier = Modifier
                                .padding(6.dp)
                                .clickable {
                                    viewModel.toggleProductType(productType)
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = viewModel.selectedProductTypes.value.contains(productType),
                                onCheckedChange = { }
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(productType.name.lowercase())
                        }
                    }
                }

                Button(
                    onClick = {
                        viewModel.applyFilter()
                        expanded = !expanded
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
                ) {
                    Text("Apply Filter")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(viewModel.filteredProducts.value.chunked(2)) { rowProducts ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        for (product in rowProducts) {
                            ProductCard(product)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductsPagePreview() {
    AppTheme {
        ProductsPage({})
    }
}