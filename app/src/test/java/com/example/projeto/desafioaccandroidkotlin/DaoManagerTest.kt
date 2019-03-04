package com.example.projeto.desafioaccandroidkotlin

import com.example.projeto.desafioaccandroidkotlin.Utils.ErrorProductEmpty
import com.example.projeto.desafioaccandroidkotlin.manager.DaoManager
import com.example.projeto.desafioaccandroidkotlin.manager.MenuManager
import com.example.projeto.desafioaccandroidkotlin.page.fragment.details.model.DetailsModel
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.MenuModel
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.Products
import com.example.projeto.desafioaccandroidkotlin.service.IMenuService
import org.junit.Assert.assertEquals
import org.junit.Test


class DaoManagerTest {

    @Test
    fun addQuantityZero() {
        val modal = DetailsModel(Products("Sugar", "", 1, 1, null), 0)
        try {
            DaoManager.instance.add(modal)
        } catch (ex: ErrorProductEmpty) {
            assertEquals(ErrorProductEmpty().message, "Você precisa de ao menos um produto")
        }
    }

    @Test
    fun listMenuSize() {
        MenuManager.instance.getMenu(object : IMenuService.CallMenu {
            override fun onCallMenu(menuModel: MenuModel?, error: String?) {
                if (error == null) {
                    if (menuModel != null) {
                       assertEquals(menuModel.products.count(), 6)
                    }
                }
            }
        })
    }

    @Test
    fun listMenuErrorNull() {
        MenuManager.instance.getMenu(object : IMenuService.CallMenu {
            override fun onCallMenu(menuModel: MenuModel?, error: String?) {
                assertEquals(error, null)
            }
        })
    }
}