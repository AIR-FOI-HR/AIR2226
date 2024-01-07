package hr.foi.air.mail_login

import hr.foi.air.core.login.LoginToken

class MailLoginToken(email: String, password: String) : LoginToken {
    private val authorizers = mapOf(
        "email" to email,
        "password" to password,
    )
    override fun getAuthorizers(): Map<String, String> {
        return authorizers
    }
}