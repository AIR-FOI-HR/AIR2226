package hr.foi.air.giveaway.entities

data class RegistrationBody(
    val first_name: String,
    val last_name: String,
    val username: String,
    val email: String,
    val password: String,
)
