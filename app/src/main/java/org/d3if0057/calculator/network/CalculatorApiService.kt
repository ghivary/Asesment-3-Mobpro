package org.d3if0057.calculator.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "ghivary/Asessment2-Mopro/master/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CalculatorApiService {
    @GET("copyright.json")
    suspend fun getCopyright(): String
}
object CalculatorApi {
    val service: CalculatorApiService by lazy {
        retrofit.create(CalculatorApiService::class.java)
    }
    fun getFoto(): String {
        return "https://raw.githubusercontent.com/ghivary/Asessment2-Mopro/master/_840478.png"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }
