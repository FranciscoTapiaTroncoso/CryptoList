package cl.desafiolatam.pruebacryptolist.ui.viewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.pruebacryptolist.databinding.CryptoItemBinding
import cl.desafiolatam.pruebacryptolist.model.crypto.Crypto
import cl.desafiolatam.pruebacryptolist.model.crypto.DataItem
import com.squareup.picasso.Picasso

class CryptoAdapter() : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {
    private val cryptoList = mutableListOf<Crypto>()

    class CryptoHolder(private val binding:CryptoItemBinding)
        :RecyclerView.ViewHolder(binding.root) {

        fun bind(crypto: Crypto){
            binding.tvCryptoSymbol.text = crypto.data[1].symbol
            binding.tvPriceUsd.text = crypto.data[1].priceUsd
            Picasso.get()
                .load(getImageFromSymbol(crypto))
                .into(binding.ivCryptoLogo)
        }

        fun getImageFromSymbol(crypto: Crypto):String{
            val symbol= crypto.data[1].symbol.lowercase()
            return "https://static.coincap.io/assets/icons/${symbol}@2x.png"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding = CryptoItemBinding.inflate(LayoutInflater
            .from(parent.context),parent,false)
        return CryptoHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(cryptoList[position])
    }

    override fun getItemCount() = cryptoList.size

    fun update(list: List<Crypto>){
        cryptoList.clear()
        cryptoList.addAll(list)
        notifyDataSetChanged()
    }
}