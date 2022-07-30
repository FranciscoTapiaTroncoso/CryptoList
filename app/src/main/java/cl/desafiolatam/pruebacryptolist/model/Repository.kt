package cl.desafiolatam.pruebacryptolist.model

import android.util.Log
import cl.desafiolatam.pruebacryptolist.model.local.CryptoApplication
import cl.desafiolatam.pruebacryptolist.model.network.RetrofitClient

class Repository {

    private val TAG = "Repository"
    private val cryptoDao = CryptoApplication.cryptoDatabase!!.cryptoDao()
    val cryptoList = cryptoDao.getAllCryptos()

    suspend fun getCryptos(){
        val response = RetrofitClient.apiService.getAllCryptos()
        when(response.isSuccessful){
            true -> {
                if(response.body() != null){
                   cryptoDao.insert(response.body()!!.data.map {
                       it.copy(timestamp = response.body()!!.timestamp)
                   })
                }else{
                    Log.d(TAG, "getCryptos: body is null ")
                }
            }
            false -> {
                Log.d(TAG, "getCryptos: error code ${response.code()}")
            }
        }
    }
}