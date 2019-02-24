package com.example.projeto.desafioaccandroidkotlin.page.fragment.menu

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.projeto.desafioaccandroidkotlin.R
import com.example.projeto.desafioaccandroidkotlin.databinding.ItemMenuBinding
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.MenuModel

class MenuAdapter constructor(private val menuModel: MenuModel, private val callClickMenu: CallClickMenu) : RecyclerView.Adapter<MenuViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MenuViewHolder {
        val itemMenuBinding = DataBindingUtil.inflate<ItemMenuBinding>(LayoutInflater.from(viewGroup.context),
            R.layout.item_menu, viewGroup, false)
        return MenuViewHolder(itemMenuBinding)
    }

    override fun getItemCount(): Int {
        return if(menuModel.products.isNotEmpty()) menuModel.products.size else 0
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {

        val items = menuModel.products[position]

        holder.setProducts(items, callClickMenu)
    }
}