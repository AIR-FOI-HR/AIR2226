package hr.foi.air.giveaway.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.foi.air.giveaway.mockdataprodutc.MockProducts
import hr.foi.air.giveaway.mockdataprodutc.Product
import hr.foi.air.giveaway.mockdataprodutc.ProductType
import kotlinx.coroutines.launch

class ProductsViewModel: ViewModel() {
    val products = mutableStateOf(listOf<Product>())
    val filteredProducts = mutableStateOf<List<Product>>(emptyList())
    val priceFilter = mutableStateOf(0.0)
    val selectedProductTypes = mutableStateOf(ProductType.values().toSet())

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val productList = MockProducts.generateMockProducts()
            products.value = productList
            applyFilter()
        }
    }

    fun applyFilter() {
        val filter = priceFilter.value
        val filteredList = products.value.filter {
            it.price >= filter && selectedProductTypes.value.contains(it.type)
        }
        filteredProducts.value = filteredList
    }

    fun toggleProductType(productType: ProductType) {
        selectedProductTypes.value = selectedProductTypes.value.toMutableSet().apply {
            if (contains(productType)) {
                remove(productType)
            } else {
                add(productType)
            }
        }
    }

}