package com.imsosoft.kotlinkoincryptoapp.service

import com.imsosoft.kotlinkoincryptoapp.model.Crypto
import com.imsosoft.kotlinkoincryptoapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ICryptoAPI {

    @GET(Constants.END_POINT)
    suspend fun getData(): Response<List<Crypto>>

}