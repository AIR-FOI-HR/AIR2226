package hr.foi.air.giveaway.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.foi.air.core.login.LoginHandler
import hr.foi.air.core.login.LoginOutcomeListener
import hr.foi.air.core.login.LoginToken

class LoginViewModel : ViewModel() {
    val identifier: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    private val _errorMessage: MutableLiveData<String> = MutableLiveData("")

    fun login(
        loginHandler: LoginHandler,
        loginToken: LoginToken,
        onSuccessfulLogin: () -> Unit,
        onFailedLogin: () -> Unit
    ) {
        loginHandler.handleLogin(loginToken, object : LoginOutcomeListener {
            override fun onSuccessfulLogin(identifier: String) {
                onSuccessfulLogin()
            }

            override fun onFailedLogin(reason: String) {
                _errorMessage.value = reason
                onFailedLogin()
            }
        })
    }
}