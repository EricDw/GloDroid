package net.publicmethod.glodroid

import retrofit2.Call

interface GloService {
    fun getUserWithPersonalAuthenticationToken(
        personalAuthenticationToken: String
    ): Call<GloUserDTO?>
}
