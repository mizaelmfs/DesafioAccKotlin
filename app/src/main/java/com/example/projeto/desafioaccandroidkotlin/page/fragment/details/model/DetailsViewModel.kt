package com.example.projeto.desafioaccandroidkotlin.page.fragment.details.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.example.projeto.desafioaccandroidkotlin.Utils.ErrorProductEmpty
import com.example.projeto.desafioaccandroidkotlin.Utils.SingleLiveEvent
import com.example.projeto.desafioaccandroidkotlin.manager.DaoManager
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.Products
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.TypeSize
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.TypeSugar

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val detailsModelLiveData = MutableLiveData<Products>()
    private val errorEmpty = SingleLiveEvent<Void>()
    private val addSuccess = SingleLiveEvent<Void>()

    var count = ObservableInt(0)
    var quantityField = ObservableField<String>()

    //Size
    var lowSizeIv = ObservableBoolean(false)
    var sizeMediumIv = ObservableBoolean(false)
    var sizeHighIv = ObservableBoolean(false)

    //Sugar
    var sugarNoIv = ObservableBoolean(false)
    var sugarLowIv = ObservableBoolean(false)
    var sugarMediumIv = ObservableBoolean(false)
    var sugarHighIv = ObservableBoolean(false)

    fun setProduct(productsParams: Products) {
        detailsModelLiveData.value = productsParams
        setSizeSugar(productsParams)
    }

    fun getProductsModel(): Products? {
        return detailsModelLiveData.value
    }

    fun onCLickMinus() {

        if (count.get() > 0) {
            count.set(count.get() -1)
        }
    }

    fun onCLickPlus() {
        count.set(count.get() +1)
    }

    fun getErrorEmpty() : SingleLiveEvent<Void> {
        return this.errorEmpty
    }

    fun getAddSucess() : SingleLiveEvent<Void> {
        return this.addSuccess
    }
    
    fun addList() {
        val detailsModel = DetailsModel(detailsModelLiveData.value, count.get())
        try {
            if (!DaoManager.instance.add(detailsModel)) {
                DaoManager.instance.update(detailsModel)
                addSuccess.call()
            }
        } catch (error : ErrorProductEmpty){
            this.errorEmpty.call()
        }
    }

    private fun setSizeSugar(product: Products) {
        when(product.getTypeSize()) {
            TypeSize.LOW -> {
                lowSizeIv.set(true)
                quantityField.set("${200} ML")
            }
            TypeSize.MEDIUM -> {
                sizeMediumIv.set(true)
                quantityField.set("${300} ML")
            }
            TypeSize.LARGE -> {
                sizeHighIv.set(true)
                quantityField.set("${400} ML")
            }
        }

        when(product.getTypeSugar()) {
            TypeSugar.NO_SUGAR -> {
                sugarNoIv.set(true)
            }
            TypeSugar.SUGAR_LOW -> {
                sugarLowIv.set(true)
            }
            TypeSugar.SUGAR_MEDIUM -> {
               sugarMediumIv.set(true)
            }
            TypeSugar.SUGAR_LARGE -> {
                sugarHighIv.set(true)
            }
        }
    }

}