package com.vibhu.nitjsr.mvvmsampleapp.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

            //Here we will create extension functions
//for displaying Toasts  -> Apna apns style hai
fun Context.toast(message: String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}
fun ProgressBar.show(){
    visibility = View.VISIBLE
}
fun ProgressBar.hide(){
    visibility = View.GONE
}