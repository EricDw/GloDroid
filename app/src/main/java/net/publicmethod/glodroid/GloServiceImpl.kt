package net.publicmethod.glodroid

import kotlinx.coroutines.Deferred
import net.publicmethod.glodroid.apis.GloAPIFactory
import retrofit2.Response

class GloServiceImpl : GloService {

    private val gloAPI = GloAPIFactory.gloAPI

    override suspend fun getUserWithPersonalAuthenticationTokenAsync(
        personalAuthenticationToken: String
    ): Deferred<Response<GloUserDTO?>> =
        gloAPI.getUserAsync(personalAuthenticationToken)
}
