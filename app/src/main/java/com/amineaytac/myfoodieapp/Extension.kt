package com.amineaytac.myfoodieapp

import android.view.View

fun View.gone(){
    visibility = View.GONE
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.click(func:() -> Unit){
    this.setOnClickListener{
        func()
    }
}