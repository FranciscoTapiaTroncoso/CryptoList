package cl.desafiolatam.pruebacryptolist.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.pruebacryptolist.databinding.CryptoItemBinding
import cl.desafiolatam.pruebacryptolist.model.data.Image
import cl.desafiolatam.pruebacryptolist.model.data.crypto.DataItem
import com.squareup.picasso.Picasso

class CryptoAdapter(val clickListener: (crypto:DataItem)->Unit): RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    private var cryptoList = mutableListOf<DataItem>()

    class CryptoHolder(private val binding: CryptoItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
        private val image = Image()
            fun bind(crypto: DataItem, clickListener: (cryptoId:DataItem)->Unit){
                binding.tvPriceUsd.text = "USD$ " + crypto.priceUsd
                binding.tvCryptoSymbol.text = crypto.symbol
                Picasso.get()
                    .load(image.getImage(crypto))
                    .into(binding.ivCryptoLogo)
                binding.root.setOnClickListener {
                    clickListener(crypto)
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding = CryptoItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(cryptoList[position], clickListener)
    }

    override fun getItemCount():Int{
        return cryptoList.size
    }

    fun update(list: List<DataItem>){
        cryptoList.clear()
        cryptoList.addAll(list)
        notifyDataSetChanged()
    }


}