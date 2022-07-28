package cl.desafiolatam.pruebacryptolist.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.pruebacryptolist.databinding.CryptoItemBinding
import cl.desafiolatam.pruebacryptolist.model.crypto.Crypto
import cl.desafiolatam.pruebacryptolist.model.crypto.DataItem
import com.squareup.picasso.Picasso

class CryptoAdapter(): RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    private var cryptoList = mutableListOf<Crypto>()


    class CryptoHolder(private val binding: CryptoItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
            fun bind(crypto: Crypto){
                val cInfo = crypto.data
                for(value in cInfo){
                    binding.tvPriceUsd.text = "USD$ " + value.priceUsd
                    binding.tvCryptoSymbol.text = value.symbol
                    Picasso.get()
                        .load("https://static.coincap.io/assets/icons/${value.symbol.lowercase()}@2x.png")
                        .into(binding.ivCryptoLogo)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding = CryptoItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(cryptoList[position])
    }

    override fun getItemCount():Int{
        return cryptoList.size
    }

    fun update(list: List<Crypto>){
        cryptoList.clear()
        cryptoList.addAll(list)
        notifyDataSetChanged()
    }


}