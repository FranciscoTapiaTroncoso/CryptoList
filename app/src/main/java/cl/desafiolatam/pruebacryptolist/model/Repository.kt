package cl.desafiolatam.pruebacryptolist.model

import android.util.Log
import cl.desafiolatam.pruebacryptolist.model.data.Crypto
import cl.desafiolatam.pruebacryptolist.model.network.RetrofitClient

class Repository {

    private val TAG = "Repository"
    suspend fun getCryptos(): Crypto?{
        var cryptoList :Crypto? = null
        val response = RetrofitClient.apiService.getAllCryptos()
        when(response.isSuccessful){
            true -> {
                if(response.body() != null){
                    cryptoList = response.body()!!
                }else{
                    Log.d(TAG, "getCryptos: body is null ")
                }
            }
            false -> {
                Log.d(TAG, "getCryptos: error code ${response.code()}")
                cryptoList = null
            }

        }
        return cryptoList
    }
}