package cl.desafiolatam.pruebacryptolist.ui.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.pruebacryptolist.databinding.CryptoItemBinding
import cl.desafiolatam.pruebacryptolist.databinding.CryptoItemNegativeBinding
import cl.desafiolatam.pruebacryptolist.model.data.Image
import cl.desafiolatam.pruebacryptolist.model.data.crypto.DataItem
import cl.desafiolatam.pruebacryptolist.ui.view.Const.NEGATIVEVALUE
import cl.desafiolatam.pruebacryptolist.ui.view.Const.POSITIVEVALUE
import com.squareup.picasso.Picasso

class CryptoAdapter(val clickListener: (crypto:DataItem)->Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var cryptoList = mutableListOf<DataItem>()
    private val image = Image()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == POSITIVEVALUE){
            val view = CryptoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            CryptoHolderWithPositivePriceChange(view)
        }else{
            val view = CryptoItemNegativeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            CryptoHolderWithNegativePriceChange(view)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return if(getItemViewType(position) == POSITIVEVALUE){
            (holder as CryptoHolderWithPositivePriceChange).bind(cryptoList[position], clickListener)
        }else{
            (holder as CryptoHolderWithNegativePriceChange).bind(cryptoList[position],clickListener)
        }
    }

    override fun getItemCount():Int{
        return cryptoList.size
    }

    inner class CryptoHolderWithPositivePriceChange(private val binding: CryptoItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(crypto: DataItem, clickListener: (cryptoId:DataItem)->Unit){
            binding.tvPriceUsd.text = "USD$ " + crypto.priceUsd
            binding.tvCryptoSymbol.text = crypto.symbol
            binding.tvPriceChange.text = crypto.changePercent24Hr
            Picasso.get()
                .load(image.getImage(crypto))
                .resize(150,150)
                .centerCrop()
                .into(binding.ivCryptoLogo)
            binding.root.setOnClickListener {
                clickListener(crypto)
            }
        }
    }

    inner class CryptoHolderWithNegativePriceChange(private val binding: CryptoItemNegativeBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(crypto: DataItem, clickListener: (cryptoId:DataItem)->Unit){
            binding.tvPriceUsdNegative.text = "USD$ " + crypto.priceUsd
            binding.tvCryptoSymbolNegative.text = crypto.symbol
            binding.tvPriceChangeNegative.text = crypto.changePercent24Hr
            Picasso.get()
                .load(image.getImage(crypto))
                .resize(150,150)
                .centerCrop()
                .into(binding.ivCryptoLogoNegative)
            binding.root.setOnClickListener {
                clickListener(crypto)
            }
        }
    }

    fun update(list: List<DataItem>){
        cryptoList.clear()
        cryptoList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val change = cryptoList[position].changePercent24Hr
        val check = "-" in change
        return if(check){
            NEGATIVEVALUE
        }else{
            POSITIVEVALUE
        }
    }

    private fun format(porcentaje: String):String{
        return ""
    }
}

private object Const{
    const val POSITIVEVALUE = 0
    const val NEGATIVEVALUE = 1
}