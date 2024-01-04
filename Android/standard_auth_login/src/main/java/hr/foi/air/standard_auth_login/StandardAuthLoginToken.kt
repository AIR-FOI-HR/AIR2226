package hr.foi.air.standard_auth_login

import hr.foi.air.core.login.LoginToken

class StandardAuthLoginToken(username: String, password: String) : LoginToken {
    private val authorizers = mapOf(
        "username" to username,
        "password" to password,
    )
    override fun getAuthorizers(): Map<String, String> {
        return authorizers
    }
}