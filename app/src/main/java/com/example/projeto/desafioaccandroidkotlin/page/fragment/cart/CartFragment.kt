package com.example.projeto.desafioaccandroidkotlin.page.fragment.cart


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.projeto.desafioaccandroidkotlin.R
import com.example.projeto.desafioaccandroidkotlin.Utils.PageAnimation
import com.example.projeto.desafioaccandroidkotlin.databinding.FragmentCartBinding
import com.example.projeto.desafioaccandroidkotlin.manager.DaoManager
import com.example.projeto.desafioaccandroidkotlin.page.BaseFragment
import com.example.projeto.desafioaccandroidkotlin.page.fragment.cart.model.CartViewModel
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.MenuFragment

class CartFragment : BaseFragment() {

    private lateinit var mBinding: FragmentCartBinding
    private lateinit var menuFragment: MenuFragment
    private lateinit var mViewModel: CartViewModel
    private lateinit var mCartAdapter : CartAdapter

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        mViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)

        mBinding.viewmodel = mViewModel

        configRecycler()
        menuFragment = MenuFragment()

        configTop()

        mViewModel.isEmptyList()

        return mBinding.root
    }

    private fun configTop() {
        mBinding.toolbar!!.titleToolbarTv.text = getString(R.string.cart)
        mBinding.toolbar!!.backIv.visibility = View.VISIBLE
        (activity as AppCompatActivity).setSupportActionBar(mBinding.toolbar!!.myToolbar)
        mBinding.toolbar!!.backIv.setOnClickListener {
            changeFragment(menuFragment, PageAnimation.SLIDE_RIGHT_TO_LEFT, R.id.container_fragment, activity!!.supportFragmentManager)
        }
    }

    private fun configRecycler() {

        val layoutManager = LinearLayoutManager(context)

        mBinding.cartRv.layoutManager = layoutManager
        mCartAdapter = CartAdapter(DaoManager.instance.getList().toList())
        mBinding.cartRv.adapter = mCartAdapter
    }

}
