package hr.foi.air.google_login

import hr.foi.air.core.login.LoginHandler
import hr.foi.air.core.login.LoginOutcomeListener
import hr.foi.air.core.login.LoginToken
import java.lang.IllegalArgumentException

class GoogleLoginHandler : LoginHandler {
    override fun handleLogin(loginToken: LoginToken, loginListener: LoginOutcomeListener) {
        if(loginToken !is GoogleLoginToken) {
            throw IllegalArgumentException("Must receive GoogleLoginToken instance for 'loginToken'!")
        }

        val authorizers = loginToken.getAuthorizers()
        val email = authorizers["email"]!!
        val password = authorizers["password"]!!

        if(email == "nikolabiskup101@gmail.com" && password == "12345678"){
            loginListener.onSuccessfulLogin("Nikola")
        } else {
            loginListener.onFailedLogin("Wrong mock credentials entered!")
        }

    }
}