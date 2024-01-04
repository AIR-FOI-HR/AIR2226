package hr.foi.air.standard_auth_login

import hr.foi.air.core.login.LoginHandler
import hr.foi.air.core.login.LoginOutcomeListener
import hr.foi.air.core.login.LoginToken
import java.lang.IllegalArgumentException
class StandardAuthLoginHandler : LoginHandler{
    override fun handleLogin(loginToken: LoginToken, loginListener: LoginOutcomeListener) {
        if(loginToken !is StandardAuthLoginToken) {
            throw IllegalArgumentException("Must receive UsernamePasswordLoginToken instance for 'loginToken'!")
        }

        val authorizers = loginToken.getAuthorizers()
        val username = authorizers["username"]
        val password = authorizers["password"]

        if (username == "nbiskup" && password == "12345678") {
            loginListener.onSuccessfulLogin("nbiskup")
        } else {
            loginListener.onFailedLogin("Wrong mock credentials entered!")
        }
    }
}