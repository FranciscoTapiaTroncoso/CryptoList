package cl.desafiolatam.pruebacryptolist.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.desafiolatam.pruebacryptolist.model.Repository
import cl.desafiolatam.pruebacryptolist.model.data.crypto.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CryptoViewModel: ViewModel() {
    private val repository = Repository()
    private val _navigateToCrypto = MutableLiveData<String?>()
    val navigateToCrypto:LiveData<String?>get()=_navigateToCrypto

    fun cryptoList(): LiveData<List<DataItem>> = repository.cryptoList

    init{
        getCryptos()
    }

    fun getCryptos() = viewModelScope.launch{
        withContext(Dispatchers.IO){
            repository.getCryptos()
        }
    }

    fun onCryptoClicked(cryptoSymbol: String){
        _navigateToCrypto.value = cryptoSymbol
    }

    fun onCryptoNavigated(){
        _navigateToCrypto.value = null
    }

}