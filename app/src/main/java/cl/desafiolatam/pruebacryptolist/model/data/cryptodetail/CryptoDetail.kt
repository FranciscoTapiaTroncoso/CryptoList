package cl.desafiolatam.pruebacryptolist.model.data.cryptodetail

import cl.desafiolatam.pruebacryptolist.model.data.crypto.DataItem
import com.google.gson.annotations.SerializedName

data class CryptoDetail(
    @field:SerializedName("data")
    val data: DataItem,
    @field:SerializedName("timestamp")
    val timestamp: Long
)
