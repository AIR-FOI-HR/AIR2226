package hr.foi.air.core.login

interface LoginOutcomeListener {
    fun onSuccessfulLogin(identifier: String)
    fun onFailedLogin(reason: String)
}