package com.sdzshn3.moviedb.other

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.gone() {
    this.visibility = GONE
}

fun View.visible() {
    this.visibility = VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}