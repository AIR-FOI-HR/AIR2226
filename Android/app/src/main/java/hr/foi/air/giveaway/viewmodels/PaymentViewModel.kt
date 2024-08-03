package hr.foi.air.giveaway.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.foi.air.giveaway.mockcards.Card
import hr.foi.air.giveaway.mockcards.MockCards

class PaymentViewModel : ViewModel() {
    private val _cardHolderName = MutableLiveData("")
    val cardHolderName: LiveData<String> = _cardHolderName

    private val _cardNumber = MutableLiveData("")
    val cardNumber: LiveData<String> = _cardNumber

    private val _expiryDate = MutableLiveData("")
    val expiryDate: LiveData<String> = _expiryDate

    private val _cvv = MutableLiveData("")
    val cvv: LiveData<String> = _cvv

    val paymentResult = MutableLiveData<String?>()

    fun updateCardHolderName(name: String) {
        _cardHolderName.value = name
    }

    fun updateCardNumber(number: String) {
        _cardNumber.value = number
    }

    fun updateExpiryDate(date: String) {
        _expiryDate.value = date
    }

    fun updateCvv(cvv: String) {
        _cvv.value = cvv
    }

    fun validatePayment(): Boolean {
        val card = Card(
            cardNumber.value.orEmpty(),
            expiryDate.value.orEmpty(),
            cvv.value.orEmpty(),
            cardHolderName.value.orEmpty()
        )
        return MockCards.isValidCard(card)
    }

    fun processPayment(onSuccess: () -> Unit, onFailure: () -> Unit) {
        if (validatePayment()) {
            paymentResult.value = "Payment Successful"
            onSuccess()
        } else {
            paymentResult.value = "Payment Failed. Check your details."
            onFailure()
        }
    }
}