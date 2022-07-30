package cl.desafiolatam.pruebacryptolist.ui.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cl.desafiolatam.pruebacryptolist.databinding.FragmentCryptoDetailBinding
import cl.desafiolatam.pruebacryptolist.model.Repository
import cl.desafiolatam.pruebacryptolist.model.data.Image
import cl.desafiolatam.pruebacryptolist.model.data.crypto.Crypto
import cl.desafiolatam.pruebacryptolist.model.data.crypto.DataItem
import cl.desafiolatam.pruebacryptolist.model.data.cryptodetail.CryptoDetail
import com.squareup.picasso.Picasso
import java.util.ArrayList


class CryptoDetailFragment : Fragment() {
    private var _binding: FragmentCryptoDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var  crypto: DataItem
    private var cryptoDetail = ArrayList<CryptoDetail>()


    private var image = Image()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCryptoDetailBinding.inflate(inflater,container,false)
        crypto = CryptoDetailFragmentArgs.fromBundle(requireArguments()).crypto
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

     fun initView(){
        with(binding){
            tvPriceUsdDetail.text = "USD$ " + crypto.priceUsd
            tvCryptoSymbolDetail.text = crypto.symbol
            tvIdDetail.text = crypto.id
            Picasso.get()
                .load(image.getImage(crypto))
                .into(ivCryptoLogoDetail)
            //Falta timestamp
            tvSupplyValue.text = crypto.supply
            tvMarketCapValue.text = crypto.marketCapUsd

            tvTimeStamp.text = timeStamFormat(crypto)
        }

    }
    fun getTimeStamp(crypto: DataItem): Long {
        var number = crypto.timestamp
        return  number
    }

    fun timeStamFormat(crypto:DataItem):String{
        val number = getTimeStamp(crypto).toString()
        val format = number.substring(0,2) + ":" + number.substring(2,4) + ":" +
                number.substring(4,6) + "." + number.substring(6,8)
        return format
    }

}