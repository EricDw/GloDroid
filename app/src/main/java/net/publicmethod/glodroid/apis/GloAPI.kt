package net.publicmethod.glodroid.apis

import net.publicmethod.glodroid.GloUserDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val GLO_BASE_URL: String = "https://gloapi.gitkraken.com/v1/glo/"
const val PAT_CONTRACT: String = "pat"
const val GLO_USER_ENDPOINT: String = "user{$PAT_CONTRACT}"

interface GloAPI {

    @GET(GLO_USER_ENDPOINT)
    fun getUser(@Query(PAT_CONTRACT) pat: String): Call<GloUserDTO?>

}