package hr.foi.air.mail_login

import hr.foi.air.core.login.LoginHandler
import hr.foi.air.core.login.LoginOutcomeListener
import hr.foi.air.core.login.LoginToken
import hr.foi.air.entities.MockDataLoader
import hr.foi.air.entities.RegistrationBody
import java.lang.IllegalArgumentException

class MailLoginHandler : LoginHandler {
    override fun handleLogin(loginToken: LoginToken, loginListener: LoginOutcomeListener) {
        if(loginToken !is MailLoginToken) {
            throw IllegalArgumentException("Must receive GoogleLoginToken instance for 'loginToken'!")
        }

        val authorizers = loginToken.getAuthorizers()
        val email = authorizers["email"]!!
        val password = authorizers["password"]!!


        val registrationUser: RegistrationBody? = MockDataLoader.getDataByEmail(email)

        if (registrationUser != null && email == registrationUser.email && password == registrationUser.password) {
            loginListener.onSuccessfulLogin(registrationUser.username)
        } else {
            loginListener.onFailedLogin("Wrong login for email!")
        }

    }

    override fun generateLoginToken(identifier: String, password: String): LoginToken {
        return MailLoginToken(identifier, password)
    }

    override fun getLabel(): String = "Email"
}