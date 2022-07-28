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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListingBinding.inflate(inflater,container,false)
        registerObserver()
        return binding.root
    }


    private fun registerObserver() {
        viewModel.cryptoList().observe(viewLifecycleOwner){
            Log.d(TAG, "registerObserver: ${it[0]}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}