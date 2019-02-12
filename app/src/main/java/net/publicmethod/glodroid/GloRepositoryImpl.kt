package net.publicmethod.glodroid

class GloRepositoryImpl(
    private val gloService: GloService
) : GloRepository {

    override fun getUserFor(personalAuthenticationToken: PersonalAuthenticationToken): GloUserDTO? {

        val user = gloService.getUserWithPersonalAuthenticationToken(
            personalAuthenticationToken.value
        ).execute().body()
        return user
    }

}
