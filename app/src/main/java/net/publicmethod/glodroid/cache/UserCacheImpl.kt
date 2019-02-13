package net.publicmethod.glodroid.cache

import android.content.Context
import androidx.core.content.edit
import net.publicmethod.glodroid.PersonalAuthenticationToken
import net.publicmethod.glodroid.UserCache

private const val GLO_DROID_PREFERENCES: String = "gloDroidPreferences"
private const val PERSONAL_AUTHENTICATION_KEY: String = "patKey"

class UserCacheImpl(
    context: Context
) : UserCache {

    private val preferences = context.getSharedPreferences(
        PERSONAL_AUTHENTICATION_KEY,
        Context.MODE_PRIVATE
    )

    override var personalAuthenticationToken: PersonalAuthenticationToken?
        get() = preferences.getString(PERSONAL_AUTHENTICATION_KEY, "").run {
            PersonalAuthenticationToken(this ?: "")
        }
        set(value) {
            value?.let { pat ->
                preferences.edit {
                    putString(PERSONAL_AUTHENTICATION_KEY, pat.value)
                }
            }
        }

    override val isUserLoggedIn: Boolean
        get() = personalAuthenticationToken?.isValid ?: false

}