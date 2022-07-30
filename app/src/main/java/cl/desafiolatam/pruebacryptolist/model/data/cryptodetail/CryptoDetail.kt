package cl.desafiolatam.pruebacryptolist.model.data.cryptodetail

import android.os.Parcelable
import cl.desafiolatam.pruebacryptolist.model.data.crypto.DataItem
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class CryptoDetail(
    @field:SerializedName("data")
    val data: DataItem,
    @field:SerializedName("timestamp")
    val timestamp: Long
){
    fun getTimeStam():Long = timestamp
}



