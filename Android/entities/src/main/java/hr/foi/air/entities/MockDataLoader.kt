package hr.foi.air.entities

object MockDataLoader {
    const val SUCCESS = 0
    const val USERNAME_CHECK_ERROR = 101
    const val USERNAME_ALREADY_USED = 102
    const val EMAIL_INVALID = 103
    const val EMAIL_ALREADY_USED = 104
    const val PASSWORD_INVALID = 105
    const val FIRST_NAME_INVALID = 106
    const val LAST_NAME_INVALID = 107

    private val dataList: MutableList<RegistrationBody> = mutableListOf(
        RegistrationBody("Nikola", "Biskup", "nbiskup", "nbiskup@foi.hr", "12345678"),
        RegistrationBody("Pero", "Kos", "pkos", "pkos@foi.hr", "12345678")
    )

    fun addData(registrationData: RegistrationBody): Int {
        if (registrationData.username.isBlank()) {
            return USERNAME_CHECK_ERROR
        }

        if (dataList.any { it.username == registrationData.username }) {
            return USERNAME_ALREADY_USED
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(registrationData.email).matches()) {
            return EMAIL_INVALID
        }

        if (dataList.any { it.email == registrationData.email }) {
            return EMAIL_ALREADY_USED
        }

        if (registrationData.password.length < 8|| !registrationData.password.any { it.isDigit() }) {
            return PASSWORD_INVALID
        }

        if (registrationData.first_name.isBlank()) {
            return FIRST_NAME_INVALID
        }

        if (registrationData.last_name.isBlank()) {
            return LAST_NAME_INVALID
        }

        dataList.add(registrationData)
        return SUCCESS
    }

    fun getAllData(): MutableList<RegistrationBody> {
        return dataList
    }

    fun getDataByUsername(username: String): RegistrationBody? {
        return dataList.find { it.username == username }
    }

    fun getDataByEmail(email: String): RegistrationBody? {
        return dataList.find { it.email == email }
    }
}