package com.imsosoft.kotlinkoincryptoapp.adapter

import com.imsosoft.kotlinkoincryptoapp.model.Crypto

interface IAdapter {
    fun onItemClicked(crypto: Crypto)
}