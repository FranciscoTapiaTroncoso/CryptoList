package cl.desafiolatam.pruebacryptolist.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.desafiolatam.pruebacryptolist.model.Repository
import cl.desafiolatam.pruebacryptolist.model.data.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CryptoViewModel: ViewModel() {
    private val repository = Repository()

    fun cryptoList(): LiveData<List<DataItem>> = repository.cryptoList

    init{
        getCryptos()
    }

    fun getCryptos() = viewModelScope.launch{
        withContext(Dispatchers.IO){
            repository.getCryptos()
        }

    }
}