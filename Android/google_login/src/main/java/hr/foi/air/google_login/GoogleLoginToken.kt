package hr.foi.air.google_login

import hr.foi.air.core.login.LoginToken

class GoogleLoginToken(email: String, password: String) : LoginToken {
    private val authorizers = mapOf(
        "email" to email,
        "password" to password,
    )
    override fun getAuthorizers(): Map<String, String> {
        return authorizers
    }
}