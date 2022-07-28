package cl.desafiolatam.pruebacryptolist.model.network

import cl.desafiolatam.pruebacryptolist.model.crypto.Crypto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {
    @GET("/v2/assets")
    suspend fun getAllCryptos(): Response<Crypto>
}

class RetrofitClient{

    companion object{
        val apiService: Api by lazy {
            instance().create(Api::class.java)
        }
        private const val BASE_URL = "https://api.coincap.io"

        fun instance() =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}