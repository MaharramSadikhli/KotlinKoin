package com.imsosoft.kotlinkoincryptoapp.viewmodel

import androidx.lifecycle.LiveData
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

    private val _cryptoList = MutableLiveData<Resource<List<Crypto>>>()
    val cryptoList: LiveData<Resource<List<Crypto>>> = _cryptoList

    private val _isError = MutableLiveData<Resource<Boolean>>()
    val isError: LiveData<Resource<Boolean>> = _isError

    private val _isLoading = MutableLiveData<Resource<Boolean>>()
    val isLoading: LiveData<Resource<Boolean>> = _isLoading


    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error -> ${throwable.localizedMessage}")
        _isError.value = Resource.error(throwable.localizedMessage ?: "error!", data = true)
    }


    fun getCryptoList() {
        _isLoading.value = Resource.loading(data = true)

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.downloadData()

            withContext(Dispatchers.Main) {
                response.data?.let {
                    _cryptoList.value = response
                    _isLoading.value = Resource.loading(data = false)
                    _isError.value = Resource.error("", data = false)
                }
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}