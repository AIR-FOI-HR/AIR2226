package hr.foi.air.core.login

interface LoginOutcomeListener {
    fun onSuccessfulLogin(username: String)
    fun onFailedLogin(reason: String)
}