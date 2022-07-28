package cl.desafiolatam.pruebacryptolist.model.crypto


import com.google.gson.annotations.SerializedName

data class Crypto(

    @field:SerializedName("data")
    val data: List<DataItem>,

    @field:SerializedName("timestamp")
    val timestamp: Long

)

data class DataItem(

    @field:SerializedName("symbol")
    val symbol: String,

    @field:SerializedName("volumeUsd24Hr")
    val volumeUsd24Hr: String,

    @field:SerializedName("marketCapUsd")
    val marketCapUsd: String,

    @field:SerializedName("priceUsd")
    val priceUsd: String,

    @field:SerializedName("vwap24Hr")
    val vwap24Hr: String,

    @field:SerializedName("changePercent24Hr")
    val changePercent24Hr: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("explorer")
    val explorer: String,

    @field:SerializedName("rank")
    val rank: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("maxSupply")
    val maxSupply: String,

    @field:SerializedName("supply")
    val supply: String
)