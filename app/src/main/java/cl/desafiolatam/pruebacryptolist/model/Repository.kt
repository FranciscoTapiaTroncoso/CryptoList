package cl.desafiolatam.pruebacryptolist.model

import android.util.Log
import cl.desafiolatam.pruebacryptolist.model.crypto.Crypto
import cl.desafiolatam.pruebacryptolist.model.network.RetrofitClient

class Repository {

    private val TAG = "Repository"
    suspend fun getCryptos(): List<Crypto>{
        val cryptoList = mutableListOf<Crypto>()
        val response = RetrofitClient.apiService.getAllCryptos()
        when(response.isSuccessful){
            true -> {
                if(response.body() != null){
                    cryptoList.addAll(response.body()!!)
                }else{
                    Log.d(TAG, "getCryptos: body is null ")
                }
            }
            false -> {
                Log.d(TAG, "getCryptos: error code ${response.code()}")
            }
        }
        return cryptoList
    }
}