package hr.foi.air.core.login

interface LoginHandler {
    fun handleLogin(loginToken: LoginToken, loginListener: LoginOutcomeListener)

    fun generateLoginToken(identifier: String, password: String): LoginToken

    fun getLabel(): String
}