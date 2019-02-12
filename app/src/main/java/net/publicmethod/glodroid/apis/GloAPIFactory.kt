package net.publicmethod.glodroid.apis

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object GloAPIFactory {

    private val retrofit = Retrofit.Builder().apply {
        baseUrl(GLO_BASE_URL)
        client(OkHttpClient.Builder().build())
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    val gloAPI: GloAPI
        get() = retrofit.create(GloAPI::class.java)

}