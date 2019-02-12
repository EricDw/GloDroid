package net.publicmethod.glodroid

interface GloRepository {
    fun getUserFor(
        personalAuthenticationToken: PersonalAuthenticationToken
    ): GloUserDTO?
}
