package com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableBoolean
import com.example.projeto.desafioaccandroidkotlin.Utils.SingleLiveEvent
import com.example.projeto.desafioaccandroidkotlin.manager.MenuManager
import com.example.projeto.desafioaccandroidkotlin.service.IMenuService

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    val loading = ObservableBoolean(true)
    private var menuModelSingleLiveEvent = SingleLiveEvent<MenuModel>()


    fun getMenuModelSingleLiveEvent() : SingleLiveEvent<MenuModel>{
        return menuModelSingleLiveEvent
    }

    fun getMenu() {
        MenuManager.instance.getMenu(object : IMenuService.CallMenu {
            override fun onCallMenu(menuModel: MenuModel?, error: String?) {
                if (error == null) {
                    if (menuModel != null) {
                        menuModelSingleLiveEvent.value = menuModel
                        loading.set(false)
                    }
                }
            }
        })
    }

}