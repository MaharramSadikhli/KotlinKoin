package com.imsosoft.kotlinkoincryptoapp.dependencies

import com.imsosoft.kotlinkoincryptoapp.repo.IRepo
import com.imsosoft.kotlinkoincryptoapp.repo.Repo
import com.imsosoft.kotlinkoincryptoapp.service.ICryptoAPI
import com.imsosoft.kotlinkoincryptoapp.util.Constants
import com.imsosoft.kotlinkoincryptoapp.viewmodel.CryptoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {


    // retrofit singleton
    single {

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ICryptoAPI::class.java)

    }


    // repo singleton
    single<IRepo> {
        Repo(get())
    }


    // view model singleton
    viewModel {
        CryptoViewModel(get())
    }

}