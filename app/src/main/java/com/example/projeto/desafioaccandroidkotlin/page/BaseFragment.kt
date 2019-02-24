package com.example.projeto.desafioaccandroidkotlin.page

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.projeto.desafioaccandroidkotlin.Utils.PageAnimation

open class BaseFragment : Fragment() {

     fun changeFragment(fragment: Fragment, pageAnimation: PageAnimation?, containerId: Int, fragmentManager: FragmentManager) {
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (pageAnimation != null) {
            val enter = pageAnimation.inTransition
            val exit = pageAnimation.outTransition
            if (enter > 0 && exit > 0) {
                fragmentTransaction.setCustomAnimations(enter, exit)
            }
        }

        fragmentTransaction.replace(containerId, fragment).commit()
    }
}