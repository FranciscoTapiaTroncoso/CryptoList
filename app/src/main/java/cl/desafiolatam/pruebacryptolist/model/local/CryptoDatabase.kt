package cl.desafiolatam.pruebacryptolist.model.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.desafiolatam.pruebacryptolist.model.data.crypto.DataItem

@Database(entities = [DataItem::class], version = 1)
abstract class CryptoDatabase: RoomDatabase(){
    abstract fun cryptoDao () : CryptoDao
}

class CryptoApplication : Application(){
    companion object{
        var cryptoDatabase : CryptoDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        cryptoDatabase = Room
            .databaseBuilder(this, CryptoDatabase::class.java,"cryptos_db")
            .build()
    }
}