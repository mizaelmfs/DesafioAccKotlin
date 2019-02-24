package com.example.projeto.desafioaccandroidkotlin.page.fragment


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.projeto.desafioaccandroidkotlin.R
import com.example.projeto.desafioaccandroidkotlin.Utils.PageAnimation
import com.example.projeto.desafioaccandroidkotlin.databinding.FragmentMenuBinding
import com.example.projeto.desafioaccandroidkotlin.page.BaseFragment

class MenuFragment : BaseFragment() {

    private lateinit var menuBinding: FragmentMenuBinding
    private lateinit var cartFragment: CartFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        menuBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        cartFragment = CartFragment()

        configTop(activity!!.supportFragmentManager)

        return menuBinding.root
    }

    private fun configTop(fragmentManager: FragmentManager) {
        menuBinding.toolbar!!.titleToolbarTv.text = getString(R.string.menu)
        menuBinding.toolbar!!.cartIv.visibility = View.VISIBLE
        (activity as AppCompatActivity).setSupportActionBar(menuBinding.toolbar!!.myToolbar)
        menuBinding.toolbar!!.cartIv.setOnClickListener {
            changeFragment(cartFragment, PageAnimation.SLIDE_LEFT_TO_RIGHT, R.id.container_fragment, fragmentManager)
        }
    }
}
