package hr.foi.air.giveaway.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import hr.foi.air.giveaway.mockdataprodutc.Product

object CartViewModel : ViewModel() {
    val cardItems = mutableStateOf(emptyList<Product>())

    fun addToCart(product : Product) {
        cardItems.value += product;
    }
}