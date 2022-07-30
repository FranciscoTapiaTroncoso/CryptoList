package cl.desafiolatam.pruebacryptolist.model.data

import cl.desafiolatam.pruebacryptolist.model.data.crypto.DataItem

class Image {
    fun getImage(crypto: DataItem):String{
        return "https://static.coincap.io/assets/icons/${crypto.symbol.lowercase()}@2x.png"
    }
}