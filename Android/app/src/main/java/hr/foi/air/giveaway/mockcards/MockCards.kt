package hr.foi.air.giveaway.mockcards

object MockCards {
    private val cardList = listOf(
        Card(
            cardNumber = "1111222233334444",
            expiryDate = "12/25",
            cvv = "123",
            cardHolderName = "Nikola Biskup"
        ),
        Card(
            cardNumber = "1234567812345678",
            expiryDate = "11/24",
            cvv = "456",
            cardHolderName = "Pero Peric"
        ),
        Card(
            cardNumber = "5555444433332222",
            expiryDate = "10/23",
            cvv = "789",
            cardHolderName = "Ana Anic"
        )
    )

    fun isValidCard(card: Card): Boolean {
        return cardList.any {
            it.cardNumber == card.cardNumber &&
            it.expiryDate == card.expiryDate &&
            it.cvv == card.cvv &&
            it.cardHolderName.equals(card.cardHolderName, ignoreCase = true)
        }
    }
}