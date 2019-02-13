package net.publicmethod.glodroid

class GloRepositoryImpl(
    private val gloService: GloService
) : GloRepository {

    override suspend fun getUserFor(personalAuthenticationToken: PersonalAuthenticationToken): GloUserDTO? {
        return gloService.getUserWithPersonalAuthenticationTokenAsync(
            personalAuthenticationToken.value
        ).await().body()
    }

}
