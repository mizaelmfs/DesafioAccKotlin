package com.example.projeto.desafioaccandroidkotlin.page

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projeto.desafioaccandroidkotlin.R
import com.example.projeto.desafioaccandroidkotlin.databinding.ActivityMainBinding
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.projeto.desafioaccandroidkotlin.Utils.PageAnimation
import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.MenuFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var menuFragment: MenuFragment
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        menuFragment = MenuFragment()
        fragmentManager = supportFragmentManager

        changeFragment(menuFragment, null, R.id.container_fragment)
    }

    private fun changeFragment(fragment: Fragment, pageAnimation: PageAnimation?, containerId: Int) {
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
