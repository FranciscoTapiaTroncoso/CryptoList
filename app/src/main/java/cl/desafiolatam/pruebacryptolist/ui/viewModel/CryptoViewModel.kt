package cl.desafiolatam.pruebacryptolist.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.desafiolatam.pruebacryptolist.model.Repository
import cl.desafiolatam.pruebacryptolist.model.data.Crypto
import kotlinx.coroutines.launch

class CryptoViewModel: ViewModel() {
    private val repository = Repository()

    private val dataItemList = MutableLiveData<Crypto>()
    fun cryptoList(): LiveData<Crypto> = dataItemList

    init{
        getCryptos()
    }

    fun getCryptos() = viewModelScope.launch{
        dataItemList.value = repository.getCryptos()
    }
}