package hr.foi.air.core.login

interface LoginToken {
    fun getAuthorizers(): Map<String, String>
}