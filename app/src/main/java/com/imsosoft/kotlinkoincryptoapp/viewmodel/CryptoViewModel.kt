package com.imsosoft.kotlinkoincryptoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imsosoft.kotlinkoincryptoapp.model.Crypto
import com.imsosoft.kotlinkoincryptoapp.repo.IRepo
import com.imsosoft.kotlinkoincryptoapp.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CryptoViewModel(private val repo: IRepo): ViewModel() {

    private lateinit var job: Job
    val cryptoList = MutableLiveData<Resource<List<Crypto>>>()
    val isError = MutableLiveData<Resource<Boolean>>()
    val isLoading = MutableLiveData<Resource<Boolean>>()


    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error -> ${throwable.localizedMessage}")
        isError.value = Resource.error(throwable.localizedMessage ?: "error!", data = true)
    }


    fun getCryptoList() {
        isLoading.value = Resource.loading(data = true)

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.downloadData()

            withContext(Dispatchers.Main) {
                response.data?.let {
                    cryptoList.value = response
                    isLoading.value = Resource.loading(data = false)
                    isError.value = Resource.error("", data = false)
                }
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}