package hr.foi.air.giveaway.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.foi.air.giveaway.entities.MockDataLoader
import hr.foi.air.giveaway.entities.RegistrationBody

class RegistrationViewModel : ViewModel() {
    val firstName: MutableLiveData<String> = MutableLiveData("")
    val lastName: MutableLiveData<String> = MutableLiveData("")
    val username: MutableLiveData<String> = MutableLiveData("")
    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    val confirmPassword: MutableLiveData<String> = MutableLiveData("")

    private val _errorMessage: MutableLiveData<String> = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    fun registerUser(onSuccess: () -> Unit, onFail: () -> Unit) {
        if (password.value != confirmPassword.value) {
            _errorMessage.value = "Passwords do not match."
            onFail()
            return
        }
        val requestBody = RegistrationBody(
            firstName.value!!,
            lastName.value!!,
            username.value!!,
            email.value!!,
            password.value!!
        )

        val result = MockDataLoader.addData(requestBody)

        if (result == MockDataLoader.SUCCESS) {
            onSuccess()
        } else {
            val errorMessage = when (result) {
                MockDataLoader.USERNAME_CHECK_ERROR -> "Check username."
                MockDataLoader.USERNAME_ALREADY_USED -> "Username is already used. Please enter another one."
                MockDataLoader.EMAIL_INVALID -> "Email is invalid."
                MockDataLoader.EMAIL_ALREADY_USED -> "Email entered is already used. Do you already have an account?"
                MockDataLoader.PASSWORD_INVALID -> "Password is invalid. Make sure it has at least 8 characters with at least 1 number."
                MockDataLoader.FIRST_NAME_INVALID -> "First name is invalid!"
                MockDataLoader.LAST_NAME_INVALID -> "Last name is invalid!"
                else -> ""
            }
            _errorMessage.value += errorMessage
            onFail()
        }
    }
}