package cl.desafiolatam.pruebacryptolist.ui.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cl.desafiolatam.pruebacryptolist.databinding.FragmentCryptoBinding
import cl.desafiolatam.pruebacryptolist.databinding.FragmentCryptoDetailBinding
import cl.desafiolatam.pruebacryptolist.model.data.crypto.DataItem
import com.squareup.picasso.Picasso


class CryptoDetailFragment : Fragment() {
    private var _binding: FragmentCryptoDetailBinding? = null
    private val binding get() = _binding!!
    private var cryptoDetail: DataItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCryptoDetailBinding.inflate(inflater,container,false)
        detailView()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun detailView(){
        Picasso.get()
            .load("https://static.coincap.io/assets/icons/${cryptoDetail!!.symbol.lowercase()}@2x.png")
            .into(binding.ivCryptoLogoDetail)

    }

}