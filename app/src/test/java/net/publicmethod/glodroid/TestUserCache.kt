package net.publicmethod.glodroid

class TestUserCache : UserCache {

    private var _personalAuthenticationToken: PersonalAuthenticationToken? = null

    override var personalAuthenticationToken: PersonalAuthenticationToken?
        get() = _personalAuthenticationToken
        set(value) {
            _personalAuthenticationToken = value
        }

    override val isUserLoggedIn: Boolean
        get() = _personalAuthenticationToken?.isValid ?: false

}
