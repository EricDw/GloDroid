package net.publicmethod.glodroid.cache

import net.publicmethod.glodroid.PersonalAuthenticationToken
import net.publicmethod.glodroid.UserCache

class UserCacheImpl : UserCache {

    override var personalAuthenticationToken: PersonalAuthenticationToken?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override val isUserLoggedIn: Boolean
        get() = false

}