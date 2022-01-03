package com.vibhu.nitjsr.mvvmsampleapp.util

import android.content.Context
import android.widget.Toast

//Here wewill create extension function for displaying Toasts  -> Apna apns style hai
fun Context.toast(message: String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}