package com.example.projeto.desafioaccandroidkotlin.page.fragment.menu

import android.support.v7.widget.RecyclerView
import com.example.projeto.desafioaccandroidkotlin.databinding.ItemMenuBinding
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.Products

class MenuViewHolder(private var binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setProducts(product : Products, callClickMenu: CallClickMenu){
        binding.products = product

        binding.menuContainer.setOnClickListener {
            callClickMenu.onClick(product)
        }
    }
}