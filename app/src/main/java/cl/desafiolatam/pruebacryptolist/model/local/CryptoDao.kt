package cl.desafiolatam.pruebacryptolist.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.desafiolatam.pruebacryptolist.model.data.DataItem

@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crypto: List<DataItem>)

    @Query("SELECT * FROM dataitem")
    fun getAllCryptos(): LiveData<List<DataItem>>
}