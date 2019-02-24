package com.example.projeto.desafioaccandroidkotlin.service

import com.example.projeto.desafioaccandroidkotlin.config.EndPoint
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.MenuModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuService private constructor(): IMenuService {

    private var instance: MenuService? = null

    companion object {
        val instance : MenuService by lazy { MenuService() }
    }

    override fun getMenu(callMenu: IMenuService.CallMenu) {
        val router  = EndPoint.RetrofitConfig.retrofit.create(EndPoint::class.java)
        val getMenus = router.getMenu()

        getMenus.enqueue(object : Callback<MenuModel> {

            override fun onResponse(call: Call<MenuModel>?, response: Response<MenuModel>?) {
                callMenu.onCallMenu(response!!.body(), null)
            }

            override fun onFailure(call: Call<MenuModel>?, t: Throwable?) {
                callMenu.onCallMenu(null, t!!.message)
            }
        })
    }
}