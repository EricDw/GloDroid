package net.publicmethod.glodroid

import kotlinx.coroutines.Deferred
import retrofit2.Response

interface GloService {
   suspend fun getUserWithPersonalAuthenticationTokenAsync(
        personalAuthenticationToken: String
       // TODO: 2019-02-12 - Do not return a nullable GloUserDTO instead handle errors correctly with a Result object
    ): Deferred<Response<GloUserDTO?>>
}
