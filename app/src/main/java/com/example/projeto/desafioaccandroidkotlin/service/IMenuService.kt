package com.example.projeto.desafioaccandroidkotlin.service

import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.MenuModel

interface IMenuService {

    fun getMenu(callMenu: CallMenu)

    interface CallMenu {
        fun onCallMenu(menuModel: MenuModel?, error: String?)
    }
}