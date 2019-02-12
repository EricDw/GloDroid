package net.publicmethod.glodroid

interface UserCache {

    var personalAuthenticationToken: PersonalAuthenticationToken?

    val isUserLoggedIn: Boolean
}
