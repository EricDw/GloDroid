package net.publicmethod.glodroid

class TestUserCache : UserCache {
    override val isUserLoggedIn: Boolean
        get() = false
}
