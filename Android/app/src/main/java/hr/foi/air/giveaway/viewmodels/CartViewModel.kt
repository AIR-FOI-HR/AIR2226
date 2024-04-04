package hr.foi.air.giveaway.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import hr.foi.air.giveaway.mockdataproduct.Product

object CartViewModel : ViewModel() {
    val cardItems = mutableStateOf(emptyList<Product>())

    fun addToCart(product: Product) {
        if (!cardItems.value.contains(product)) {
            cardItems.value += product
        }
    }

    fun removeFromCart(product: Product) {
        cardItems.value -=  product
    }

    fun increaseQuantity(product: Product) {
        val updatedList = cardItems.value.toMutableList()
        val index = updatedList.indexOf(product)
        if (index != -1) {
            val updatedProduct = product.copy(productQuantity = product.productQuantity + 1)
            updatedList[index] = updatedProduct
            cardItems.value = updatedList
        }
    }

    fun decreaseQuantity(product: Product) {
        val updatedList = cardItems.value.toMutableList()
        val index = updatedList.indexOf(product)
        if (index != -1) {
            val updatedProduct = product.copy(productQuantity = maxOf(product.productQuantity - 1, 0))
            updatedList[index] = updatedProduct
            cardItems.value = updatedList
        }
    }

}