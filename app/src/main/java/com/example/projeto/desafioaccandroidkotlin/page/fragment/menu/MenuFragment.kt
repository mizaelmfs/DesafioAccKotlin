package com.example.projeto.desafioaccandroidkotlin.page.fragment.menu


import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.projeto.desafioaccandroidkotlin.R
import com.example.projeto.desafioaccandroidkotlin.Utils.NetworkChange
import com.example.projeto.desafioaccandroidkotlin.Utils.PageAnimation
import com.example.projeto.desafioaccandroidkotlin.databinding.FragmentMenuBinding
import com.example.projeto.desafioaccandroidkotlin.page.BaseFragment
import com.example.projeto.desafioaccandroidkotlin.page.fragment.cart.CartFragment
import com.example.projeto.desafioaccandroidkotlin.page.fragment.details.DetailsFragment
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.MenuViewModel
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.Products
import org.jetbrains.anko.support.v4.toast

class MenuFragment : BaseFragment(), CallClickMenu {

    private lateinit var mBinding: FragmentMenuBinding
    private lateinit var cartFragment: CartFragment
    private lateinit var detailsFragment: DetailsFragment
    private lateinit var mViewModel: MenuViewModel
    private lateinit var networkChange: NetworkChange
    private lateinit var mMenuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        mViewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)
        mBinding.viewmodel = mViewModel

        cartFragment = CartFragment()
        detailsFragment = DetailsFragment()
        configTop()

        networkChange =
            NetworkChange(context = activity!!.baseContext)

        getMenu()
        configRecycler()

        return mBinding.root
    }

    private fun configTop() {
        mBinding.toolbar!!.titleToolbarTv.text = getString(R.string.menu)
        mBinding.toolbar!!.cartIv.visibility = View.VISIBLE
        (activity as AppCompatActivity).setSupportActionBar(mBinding.toolbar!!.myToolbar)
        mBinding.toolbar!!.cartIv.setOnClickListener {
            changeFragment(cartFragment, PageAnimation.SLIDE_LEFT_TO_RIGHT, R.id.container_fragment, activity!!.supportFragmentManager)
        }
    }

    private fun getMenu() {
        if (networkChange.hasInternetConnection()) {
            mViewModel.getMenu()
        } else {
            val builder = AlertDialog.Builder(context)
                .setTitle(R.string.title_dialog_internet_error)
                .setPositiveButton(R.string.body_dialog_internet_error_retry) {
                        dialogInterface, _ ->
                    run {
                        dialogInterface.dismiss()
                        getMenu()
                    }
                }
                .setNegativeButton(R.string.finish) {
                        _, _ -> mViewModel.loading.set(false)
                }
                .setCancelable(false)
                .create()
            builder.show()
        }
    }

    private fun configRecycler() {

        val layoutManager = LinearLayoutManager(context)

        mViewModel.getMenuModelSingleLiveEvent().observe(this, Observer {
            mBinding.rvMenu.layoutManager = layoutManager
            mMenuAdapter = MenuAdapter(it!!, this)
            mBinding.rvMenu.adapter = mMenuAdapter
        })
    }

    override fun onClick(product: Products) {
        val bundle = Bundle()
        bundle.putSerializable("product", product)
        detailsFragment.arguments = bundle
        changeFragment(detailsFragment, PageAnimation.SLIDE_LEFT_TO_RIGHT, R.id.container_fragment, activity!!.supportFragmentManager)
    }
}
