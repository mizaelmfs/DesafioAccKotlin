package com.example.projeto.desafioaccandroidkotlin.manager

import com.example.projeto.desafioaccandroidkotlin.Utils.ErrorProductEmpty
import com.example.projeto.desafioaccandroidkotlin.page.fragment.details.model.DetailsModel

class DaoManager private constructor() {

    private var listDao: MutableSet<DetailsModel> = mutableSetOf()

    companion object {
        val instance : DaoManager by lazy { DaoManager() }
    }

    fun add (detailsModel: DetailsModel): Boolean {
        if (detailsModel.count > 0) {
            return listDao.add(detailsModel)
        }
        throw ErrorProductEmpty()
    }

    fun update (detailsModel: DetailsModel): Boolean {
        listDao.remove(detailsModel)
        return this.add(detailsModel)
    }

    fun getList () : MutableSet<DetailsModel> {
        return listDao
    }

}
