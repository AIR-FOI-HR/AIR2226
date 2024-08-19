package hr.foi.air.giveaway.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.foi.air.giveaway.mockdataproduct.ProductRepository
import hr.foi.air.giveaway.mockdataproduct.Product
import hr.foi.air.giveaway.mockdataproduct.ProductType
import kotlinx.coroutines.launch

class ProductsViewModel: ViewModel() {
    private val products = mutableStateOf(listOf<Product>())
    val filteredProducts = mutableStateOf<List<Product>>(emptyList())
    val minPriceFilter = mutableStateOf(0.0)
    val maxPriceFilter = mutableStateOf(200.0)
    val selectedProductTypes = mutableStateOf(ProductType.values().toSet())

    fun loadProducts(context: Context) {
        viewModelScope.launch {
            val productList = ProductRepository.getAllProducts(context)
            products.value = productList
            applyFilter()
        }
    }

    fun applyFilter() {
        val filterMin = minPriceFilter.value
        val filterMax = maxPriceFilter.value
        val filteredList = products.value.filter {
            it.price in filterMin..filterMax && selectedProductTypes.value.contains(it.type)
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