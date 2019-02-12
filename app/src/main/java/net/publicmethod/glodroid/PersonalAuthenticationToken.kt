package net.publicmethod.glodroid

const val VALID_PAT_LENGTH: Int = 41

class PersonalAuthenticationToken(val value: String = "") {
    val isValid: Boolean
        get() = value.length >= VALID_PAT_LENGTH
}
