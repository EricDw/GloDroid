package net.publicmethod.glodroid.cache

import net.publicmethod.glodroid.UserCache

class UserCacheImpl : UserCache {
    override val isUserLoggedIn: Boolean
        get() = false
}