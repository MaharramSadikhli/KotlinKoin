package com.imsosoft.kotlinkoincryptoapp.repo

import com.imsosoft.kotlinkoincryptoapp.model.Crypto
import com.imsosoft.kotlinkoincryptoapp.util.Resource

interface IRepo {
    suspend fun downloadData(): Resource<List<Crypto>>
}