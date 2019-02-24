package com.example.projeto.desafioaccandroidkotlin.page.fragment


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.projeto.desafioaccandroidkotlin.R
import com.example.projeto.desafioaccandroidkotlin.Utils.PageAnimation
import com.example.projeto.desafioaccandroidkotlin.databinding.FragmentDetailsBinding
import com.example.projeto.desafioaccandroidkotlin.page.BaseFragment

class DetailsFragment : BaseFragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding
    private lateinit var menuFragment: MenuFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        detailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        menuFragment = MenuFragment()

        configTop(activity!!.supportFragmentManager)

        return detailsBinding.root
    }

    private fun configTop(fragmentManager: FragmentManager) {
        detailsBinding.toolbar!!.titleToolbarTv.text = getString(R.string.details)
        detailsBinding.toolbar!!.backIv.visibility = View.VISIBLE
        (activity as AppCompatActivity).setSupportActionBar(detailsBinding.toolbar!!.myToolbar)
        detailsBinding.toolbar!!.backIv.setOnClickListener {
            changeFragment(menuFragment, PageAnimation.SLIDE_RIGHT_TO_LEFT, R.id.container_fragment, fragmentManager)
        }
    }

}
