package com.ajidroid.amphibians.data

import com.ajidroid.amphibians.network.AmphiApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val amphibiansRepository : AmphibiansRepository
}
class DefaultAppContainer() : AppContainer{

    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService : AmphiApiService by lazy {
        retrofit.create(AmphiApiService::class.java)
    }

    override val amphibiansRepository: AmphibiansRepository by lazy {
        NetworkAmphibianRepository(retrofitService)
    }

}