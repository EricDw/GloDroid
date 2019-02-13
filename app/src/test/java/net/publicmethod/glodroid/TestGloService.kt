@file:Suppress("SpellCheckingInspection")

package net.publicmethod.glodroid

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import net.publicmethod.glodroid.apis.GloAPI
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestGloService(
    private val mockWebServer: MockWebServer
) : GloService {

//    private val gloAPI = GloAPIFactory.gloAPI


    private val retrofit = Retrofit.Builder().apply {
        baseUrl(mockWebServer.url("/").toString())
        addCallAdapterFactory(CoroutineCallAdapterFactory())
        addConverterFactory(GsonConverterFactory.create())
        client(OkHttpClient.Builder().build())
    }.build()

    private val gloAPI: GloAPI
        get() = retrofit.create(GloAPI::class.java)


    override suspend fun getUserWithPersonalAuthenticationTokenAsync(
        personalAuthenticationToken: String
    ): Deferred<Response<GloUserDTO?>> {
        mockWebServer.enqueue(MockResponse().apply {
            setResponseCode(200)
            setBody("""{"username":"ericdewildt","id":"3ee66ec9-bd37-48cd-be87-2342dkdjj3"}""")
        })

        return gloAPI.getUserAsync(personalAuthenticationToken)

    }
}
