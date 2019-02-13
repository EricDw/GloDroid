package net.publicmethod.glodroid

interface GloRepository {
    suspend fun getUserFor(
        personalAuthenticationToken: PersonalAuthenticationToken
    ): GloUserDTO?
}
