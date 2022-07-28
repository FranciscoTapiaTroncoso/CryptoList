package cl.desafiolatam.pruebacryptolist.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.desafiolatam.pruebacryptolist.model.Repository
import cl.desafiolatam.pruebacryptolist.model.crypto.Crypto
import kotlinx.coroutines.launch

class CryptoViewModel: ViewModel() {
    private val repository = Repository()

    private val cryptoList = MutableLiveData<List<Crypto>>()

    fun cryptoList(): LiveData<List<Crypto>> = cryptoList

    init{
        getCryptos()
    }

    fun getCryptos() = viewModelScope.launch{
        cryptoList.value = repository.getCryptos()
    }
}