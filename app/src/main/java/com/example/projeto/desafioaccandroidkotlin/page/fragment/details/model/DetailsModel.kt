package com.example.projeto.desafioaccandroidkotlin.page.fragment.details.model

import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.Products

 data class DetailsModel (
     var products: Products?,
     var count: Int


 ) {
     override fun equals(other: Any?): Boolean {
         if (this === other) return true
         if (javaClass != other?.javaClass) return false

         other as DetailsModel

         if (products != other.products) return false

         return true
     }

     override fun hashCode(): Int {
         return products?.hashCode() ?: 0
     }
 }