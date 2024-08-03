package hr.foi.air.giveaway.navigation.components.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.foi.air.giveaway.viewmodels.PaymentViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.input.ImeAction

@Composable
fun PaymentProcessingPage(
    totalCartPrice: Double,
    onPaymentSuccess: () -> Unit,
    onCancelPayment: () -> Unit,
    paymentViewModel: PaymentViewModel = viewModel()
) {
    val cardHolderName by paymentViewModel.cardHolderName.observeAsState("")
    val cardNumber by paymentViewModel.cardNumber.observeAsState("")
    val expiryDate by paymentViewModel.expiryDate.observeAsState("")
    val cvv by paymentViewModel.cvv.observeAsState("")
    val paymentResult by paymentViewModel.paymentResult.observeAsState()

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Total Price: $$totalCartPrice",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = cardHolderName,
            onValueChange = { paymentViewModel.updateCardHolderName(it) },
            label = { Text("Card Holder Name") },
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = cardNumber,
            onValueChange = { paymentViewModel.updateCardNumber(it) },
            label = { Text("Card Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) }
            ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = expiryDate,
            onValueChange = { paymentViewModel.updateExpiryDate(it) },
            label = { Text("Expiry Date (MM/YY)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) }
            ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = cvv,
            onValueChange = { paymentViewModel.updateCvv(it) },
            label = { Text("CVV") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                paymentViewModel.processPayment(
                    onSuccess = { onPaymentSuccess() },
                    onFailure = { }
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
        ) {
            Text("Pay Now", color = Color.White)
        }

        Button(
            onClick = { onCancelPayment() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant)
        ) {
            Text("Cancel", color = Color.White)
        }

        paymentResult?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
@Preview
@Composable
fun PaymentProcessingPagePreview() {
    PaymentProcessingPage(totalCartPrice = 100.00, onPaymentSuccess = {}, onCancelPayment = {})
}