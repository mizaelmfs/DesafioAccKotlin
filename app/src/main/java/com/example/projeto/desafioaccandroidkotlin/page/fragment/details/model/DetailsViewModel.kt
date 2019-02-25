package com.example.projeto.desafioaccandroidkotlin.page.fragment.details.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.projeto.desafioaccandroidkotlin.Utils.SingleLiveEvent
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.Products

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val detailsModelLiveData = MutableLiveData<Products>()
    private var count = SingleLiveEvent<Int>()
    private var minusPlus : Int = 0

    fun setProduct(productsParams: Products) {
        detailsModelLiveData.value = productsParams
        count.value = minusPlus
    }

    fun getProductsModel(): Products? {
        return detailsModelLiveData.value
    }

    fun setMinus(view : View) {

        if (minusPlus > 0) {
            minusPlus--
            count.value = minusPlus
        }

    }

    fun setPlus(view : View) {
        minusPlus++
        count.value = minusPlus
    }

    fun getCount() : SingleLiveEvent<Int> {
        return count
    }

}