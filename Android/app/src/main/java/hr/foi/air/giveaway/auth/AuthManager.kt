package hr.foi.air.giveaway.auth


import hr.foi.air.core.login.LoginHandler

class AuthManager(private val loginHandlers: List<LoginHandler>) {
    fun getAvailableLoginHandlers(): List<LoginHandler> {
        return loginHandlers
    }
}