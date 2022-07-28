package cl.desafiolatam.pruebacryptolist.ui.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.pruebacryptolist.databinding.CryptoItemBinding
import cl.desafiolatam.pruebacryptolist.model.crypto.Crypto
import com.squareup.picasso.Picasso

class CryptoAdapter(): RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    private val cryptoList = mutableListOf<Crypto>()

    class CryptoHolder(private val binding: CryptoItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
            fun bind(crypto: Crypto){
                val cInfo = crypto.cryptodata
                for(value in cInfo){
                    binding.tvPriceUsd.text = value.priceUsd
                    binding.tvCryptoSymbol.text = value.symbol
                    Picasso.get()
                        .load("https://static.coincap.io/assets/icons/${value.symbol.lowercase()}@2x.png")
                        .into(binding.ivCryptoLogo)
                }

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount()= cryptoList.size


}