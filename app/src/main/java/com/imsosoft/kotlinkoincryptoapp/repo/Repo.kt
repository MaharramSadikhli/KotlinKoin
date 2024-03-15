package com.imsosoft.kotlinkoincryptoapp.repo

import com.imsosoft.kotlinkoincryptoapp.model.Crypto
import com.imsosoft.kotlinkoincryptoapp.service.ICryptoAPI
import com.imsosoft.kotlinkoincryptoapp.util.Resource

class Repo(private val api: ICryptoAPI): IRepo {
    override suspend fun downloadData(): Resource<List<Crypto>> {
        return try {
            val response = api.getData()

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }

        } catch (e: Exception) {
            Resource.error("No data $e", null)
        }
    }
}