package com.ajidroid.amphibians.network

import com.ajidroid.amphibians.model.Amphibian
import retrofit2.http.GET


//    private const val BASE_URL =
//        "https://android-kotlin-fun-mars-server.appspot.com/"
//
//    private val retrofit = Retrofit.Builder()
//        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//        .baseUrl(BASE_URL)
//        .build()

    interface AmphiApiService {
        @GET("amphibians")
        suspend fun getData() : List<Amphibian>
    }

//    object AmphiApi{
//        val retrofitService : AmphiApiService by lazy {
//            retrofit.create(AmphiApiService::class.java)
//        }
//    }