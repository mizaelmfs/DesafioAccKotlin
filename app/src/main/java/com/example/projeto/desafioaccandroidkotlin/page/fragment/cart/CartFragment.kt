package com.example.projeto.desafioaccandroidkotlin.page.fragment.cart


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.projeto.desafioaccandroidkotlin.R
import com.example.projeto.desafioaccandroidkotlin.Utils.PageAnimation
import com.example.projeto.desafioaccandroidkotlin.databinding.FragmentCartBinding
import com.example.projeto.desafioaccandroidkotlin.page.BaseFragment
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.MenuFragment

class CartFragment : BaseFragment() {

    private lateinit var cartBinding: FragmentCartBinding
    private lateinit var menuFragment: MenuFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        cartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)

        menuFragment = MenuFragment()

        configTop()

        return cartBinding.root
    }

    private fun configTop() {
        cartBinding.toolbar!!.titleToolbarTv.text = getString(R.string.cart)
        cartBinding.toolbar!!.backIv.visibility = View.VISIBLE
        (activity as AppCompatActivity).setSupportActionBar(cartBinding.toolbar!!.myToolbar)
        cartBinding.toolbar!!.backIv.setOnClickListener {
            changeFragment(menuFragment, PageAnimation.SLIDE_RIGHT_TO_LEFT, R.id.container_fragment, activity!!.supportFragmentManager)
        }
    }

}
