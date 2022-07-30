package cl.desafiolatam.pruebacryptolist.ui.view

import android.content.Context
import android.content.SharedPreferences
import android.hardware.SensorManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import cl.desafiolatam.pruebacryptolist.databinding.FragmentCryptoBinding
import cl.desafiolatam.pruebacryptolist.ui.viewModel.CryptoViewModel


class CryptoFragment : Fragment() {
    private var _binding: FragmentCryptoBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CryptoViewModel>()
    private val TAG = "ListingFragment"
    private lateinit var cryptoadapter: CryptoAdapter
    lateinit var preferences:SharedPreferences

    override fun onAttach(context: Context) {
        super.onAttach(context)
        preferences = context.getSharedPreferences("preferencia",Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCryptoBinding.inflate(inflater,container,false)
        initView()
        registerObserver()
        onClick()
        return binding.root
    }

    private fun initView() {
        cryptoadapter = CryptoAdapter{
            cryptoSymbol ->  Toast.makeText(context,
            "Clicked crypto ${cryptoSymbol.symbol}",
            Toast.LENGTH_SHORT).show()
            navigation()
            viewModel.onCryptoClicked(cryptoSymbol)
        }
        binding.rvCryptoList.adapter = cryptoadapter
        binding.rvCryptoList.layoutManager = GridLayoutManager(context, 1)

        var name = preferences.getString("USERNAME","")
        Log.d(TAG, "initView: ${name}")
        binding.etUsuario.setText(name)

        binding.etUsuario.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                preferences.edit().putString("USERNAME",s.toString()).apply()
                var name = preferences.getString("USERNAME","")
                Log.d(TAG, "afterinitView: ${name}")
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }


    private fun registerObserver() {
        viewModel.cryptoList().observe(viewLifecycleOwner){
            Log.d(TAG, "registerObserver: ${it}")
            //Actualizar lista con objeto no nulo
            it?.let{
                cryptoadapter.update(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigation(){
        viewModel.navigateToCrypto.observe(viewLifecycleOwner, Observer{crypto ->
            crypto?.let {
                val action = CryptoFragmentDirections
                    .actionCryptoFragmentToCryptoDetailFragment(crypto)
                this.findNavController().navigate(action)
                viewModel.onCryptoNavigated()
            }
        })
    }

    fun onClick() {
        val editTextClickListener: View.OnClickListener = View.OnClickListener { v ->
            if (v.id == binding.etUsuario.getId()) {
                binding.etUsuario.setCursorVisible(true)
            }
        }
        binding.etUsuario.setOnClickListener(editTextClickListener)
    }

}

