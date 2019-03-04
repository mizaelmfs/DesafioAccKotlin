package com.example.projeto.desafioaccandroidkotlin.page.fragment.cart

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.projeto.desafioaccandroidkotlin.R
import com.example.projeto.desafioaccandroidkotlin.databinding.ItemCartBinding
import com.example.projeto.desafioaccandroidkotlin.page.fragment.details.model.DetailsModel

class CartAdapter constructor(private val listDetails: List<DetailsModel>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CartViewHolder {
        val itemCartBinding = DataBindingUtil.inflate<ItemCartBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_cart, viewGroup, false)
        return CartViewHolder(itemCartBinding)
    }

    override fun getItemCount(): Int {
        return if(listDetails.isNotEmpty()) listDetails.size else 0
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        val details = listDetails[position]

        holder.setDetails(details)

    }

    class CartViewHolder(var binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setDetails(detailsModel: DetailsModel) {
            binding.details = detailsModel
            var item = ""
            if (!detailsModel.products!!.additional.isNullOrEmpty()) {
                detailsModel.products!!.additional!!.forEach {
                    item += "$it "
                }
                binding.addTxt.text = item
            }
        }
    }
}