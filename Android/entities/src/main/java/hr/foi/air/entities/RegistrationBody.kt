package hr.foi.air.entities

data class RegistrationBody(
    val first_name: String,
    val last_name: String,
    val username: String,
    val email: String,
    val number: String,
    val password: String,
)
