package com.example.projeto.desafioaccandroidkotlin.manager

import com.example.projeto.desafioaccandroidkotlin.service.IMenuService
import com.example.projeto.desafioaccandroidkotlin.service.MenuService

class MenuManager private constructor() {

    private var instance: MenuManager? = null
    private var menuService: MenuService = MenuService.instance

    companion object {
        val instance : MenuManager by lazy { MenuManager() }
    }

    fun getMenu(callMenu: IMenuService.CallMenu) {
        menuService.getMenu(callMenu)
    }

}