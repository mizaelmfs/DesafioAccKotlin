package com.example.projeto.desafioaccandroidkotlin.page.fragment.details.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableInt
import android.view.View
import com.example.projeto.desafioaccandroidkotlin.Utils.SingleLiveEvent
import com.example.projeto.desafioaccandroidkotlin.manager.DaoManager
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.Products

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val detailsModelLiveData = MutableLiveData<Products>()
    private val errorEmpty = SingleLiveEvent<Void>()
    var count = ObservableInt(0)

    fun setProduct(productsParams: Products) {
        detailsModelLiveData.value = productsParams
    }

    fun getProductsModel(): Products? {
        return detailsModelLiveData.value
    }

    fun setMinus(view : View) {

        if (count.get() > 0) {
            count.set(count.get() -1)
        }

    }

    fun setPlus(view : View) {
        count.set(count.get() +1)
    }

    fun getErrorEmpty() : SingleLiveEvent<Void> {
        return this.errorEmpty
    }
    
    fun addList(view : View) {
        if (count.get() > 0) {
            val detailsModel = DetailsModel(detailsModelLiveData.value, count.get())
            if (!DaoManager.instance.add(detailsModel)) {
                DaoManager.instance.update(detailsModel)
            }
        } else {
            this.errorEmpty.call()
        }
    }

}