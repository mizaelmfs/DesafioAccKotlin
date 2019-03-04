package com.example.projeto.desafioaccandroidkotlin.page.fragment.cart.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableBoolean
import com.example.projeto.desafioaccandroidkotlin.manager.DaoManager

class CartViewModel(application: Application) : AndroidViewModel(application) {

    val emptyList = ObservableBoolean(true)

    fun isEmptyList() {
        if (DaoManager.instance.getList().count() > 0) {
            emptyList.set(false)
        } else {
            emptyList.set(true)
        }

    }

}