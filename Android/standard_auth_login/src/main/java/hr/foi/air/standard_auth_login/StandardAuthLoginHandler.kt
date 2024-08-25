package hr.foi.air.standard_auth_login

import hr.foi.air.core.login.LoginHandler
import hr.foi.air.core.login.LoginOutcomeListener
import hr.foi.air.core.login.LoginToken
import java.lang.IllegalArgumentException
import hr.foi.air.entities.RegistrationBody
import hr.foi.air.entities.MockDataLoader


class StandardAuthLoginHandler : LoginHandler{
    override fun handleLogin(loginToken: LoginToken, loginListener: LoginOutcomeListener) {
        if(loginToken !is StandardAuthLoginToken) {
            throw IllegalArgumentException("Must receive StandardAuthLoginHandler instance for 'loginToken'!")
        }

        val authorizers = loginToken.getAuthorizers()
        val username = authorizers["username"]!!
        val password = authorizers["password"]!!

        val registrationUser: RegistrationBody? = MockDataLoader.getDataByUsername(username)

        if (registrationUser != null && username == registrationUser.username && password == registrationUser.password) {
            loginListener.onSuccessfulLogin(username)
        } else {
            loginListener.onFailedLogin("Wrong login for username!")
        }
    }

    override fun generateLoginToken(identifier: String, password: String): LoginToken {
        return StandardAuthLoginToken(identifier, password)
    }

    override fun getLabel(): String = "Standard"
}