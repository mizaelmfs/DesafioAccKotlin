package com.example.projeto.desafioaccandroidkotlin.page.fragment.details


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projeto.desafioaccandroidkotlin.R
import com.example.projeto.desafioaccandroidkotlin.Utils.PageAnimation
import com.example.projeto.desafioaccandroidkotlin.databinding.FragmentDetailsBinding
import com.example.projeto.desafioaccandroidkotlin.databinding.ItemAddContainerBinding
import com.example.projeto.desafioaccandroidkotlin.page.BaseFragment
import com.example.projeto.desafioaccandroidkotlin.page.fragment.details.model.DetailsViewModel
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.MenuFragment
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.Products
import org.jetbrains.anko.support.v4.toast

class DetailsFragment : BaseFragment() {

    private lateinit var mBinding: FragmentDetailsBinding
    private lateinit var menuFragment: MenuFragment
    private lateinit var mViewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        mViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        mBinding.viewmodel = mViewModel

        menuFragment = MenuFragment()
        configTop()

        val product = arguments!!["product"] as Products

        mViewModel.setProduct(product)

        inflaterItem(product)
        subscriberObservable()

        return mBinding.root
    }

    private fun configTop() {
        mBinding.toolbar!!.titleToolbarTv.text = getString(R.string.details)
        mBinding.toolbar!!.backIv.visibility = View.VISIBLE
        (activity as AppCompatActivity).setSupportActionBar(mBinding.toolbar!!.myToolbar)
        mBinding.toolbar!!.backIv.setOnClickListener {
            changeFragment(menuFragment, PageAnimation.SLIDE_RIGHT_TO_LEFT, R.id.container_fragment, activity!!.supportFragmentManager)
        }
    }

    private fun inflaterItem(product: Products) {
        if (!product.additional.isNullOrEmpty()) {
            val inflater = LayoutInflater.from(mBinding.addContainer.context)
            var inflaterImageBinding: ItemAddContainerBinding
            product.additional!!.forEach {
                inflaterImageBinding = DataBindingUtil.inflate(
                    inflater, R.layout.item_add_container,
                    mBinding.addContainer, false
                )

                when (it) {
                    "chantilly" -> inflaterImageBinding.addItemIv.setBackgroundResource(R.drawable.mocha)
                    "chocolate" -> inflaterImageBinding.addItemIv.setBackgroundResource(R.drawable.chocolate)
                    "coffee" -> inflaterImageBinding.addItemIv.setBackgroundResource(R.drawable.coffe)
                    "cinnamon" -> inflaterImageBinding.addItemIv.setBackgroundResource(R.drawable.cinnamon)
                    "milk" -> inflaterImageBinding.addItemIv.setBackgroundResource(R.drawable.milk)
                }

                mBinding.addContainer.addView(inflaterImageBinding.addItemIv)
            }
        }
    }

    private  fun subscriberObservable() {
        this.mViewModel.getErrorEmpty().observe(this, Observer {
            toast(getString(R.string.error_empty))
        })
    }

}
