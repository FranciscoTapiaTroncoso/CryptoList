package cl.desafiolatam.pruebacryptolist.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import cl.desafiolatam.pruebacryptolist.databinding.FragmentListingBinding
import cl.desafiolatam.pruebacryptolist.ui.viewModel.CryptoViewModel

class ListingFragment : Fragment() {
    private var _binding: FragmentListingBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CryptoViewModel>()
    private val TAG = "ListingFragment"
    private lateinit var cryptoadapter: CryptoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListingBinding.inflate(inflater,container,false)
        initView()
        registerObserver()
        return binding.root
    }

    private fun initView() {
        cryptoadapter = CryptoAdapter()
        binding.rvCryptoList.adapter = cryptoadapter
        binding.rvCryptoList.layoutManager = GridLayoutManager(context, 1)
    }


    private fun registerObserver() {
        viewModel.cryptoList().observe(viewLifecycleOwner){
            Log.d(TAG, "registerObserver: ${it}")
            //Actualizar lista con objeto no nulo
            it?.let{
                cryptoadapter.update(it.data)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}