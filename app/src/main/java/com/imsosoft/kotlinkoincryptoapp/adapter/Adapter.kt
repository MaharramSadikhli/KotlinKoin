package com.imsosoft.kotlinkoincryptoapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imsosoft.kotlinkoincryptoapp.databinding.RecyclerRowBinding
import com.imsosoft.kotlinkoincryptoapp.model.Crypto
import com.imsosoft.kotlinkoincryptoapp.model.CryptoArrayList
import com.imsosoft.kotlinkoincryptoapp.util.Constants

class Adapter(
    private val cryptoList: ArrayList<Crypto>,
    private val listener: IAdapter
) : RecyclerView.Adapter<Adapter.AdapterViewHolder>() {

    private val colors: Array<String> = arrayOf(
        Constants.RED,
        Constants.BLUE,
        Constants.DARK_BLUE,
        Constants.ORANGE,
        Constants.PURPLE,
        Constants.BLACK,
        Constants.BROWN,
        Constants.GREEN
    )

    class AdapterViewHolder(val binding: RecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerRowBinding.inflate(layoutInflater, parent, false)
        return AdapterViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
        holder.binding.cryptoNameText.text = cryptoList[position].currency
        holder.binding.cryptoPriceText.text = cryptoList[position].price

        holder.itemView.setOnClickListener {
            listener.onItemClicked(cryptoList[position])
        }
    }


    override fun getItemCount(): Int {
        return cryptoList.size
    }
}