package com.imsosoft.kotlinkoincryptoapp.service

import com.imsosoft.kotlinkoincryptoapp.model.CryptoList
import com.imsosoft.kotlinkoincryptoapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ICryptoAPI {

    @GET(Constants.BASE_URL)
    suspend fun getData(): Response<CryptoList>

}