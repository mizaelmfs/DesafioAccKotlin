package com.example.projeto.desafioaccandroidkotlin.Utils

import com.example.projeto.desafioaccandroidkotlin.R

enum class PageAnimation constructor(var inTransition: Int, var outTransition: Int) {

    SLIDE_LEFT_TO_RIGHT(R.animator.slide_in_right, R.animator.slide_out_right),
    SLIDE_RIGHT_TO_LEFT(R.animator.slide_in_left, R.animator.slide_out_left),
    SLIDE_BOTTOM_TO_UP(R.animator.slide_in_bottom, R.animator.slide_out_top),
    SLIDE_UP_TO_BOTTOM(R.animator.slide_out_top, R.animator.slide_out_bottom),
    STATIONARY(R.animator.stationary, R.animator.stationary);

}