package net.publicmethod.glodroid.apis

import kotlinx.coroutines.Deferred
import net.publicmethod.glodroid.GloUserDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val GLO_BASE_URL: String = "https://gloapi.gitkraken.com/v1/glo/"

interface GloAPI {

    @GET("user")
    fun getUserAsync(@Query("access_token") pat: String): Deferred<Response<GloUserDTO?>>

}