package com.vibhu.nitjsr.mvvmsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vibhu.nitjsr.mvvmsampleapp.R
import com.vibhu.nitjsr.mvvmsampleapp.databinding.ActivityLoginBinding
import com.vibhu.nitjsr.mvvmsampleapp.util.hide
import com.vibhu.nitjsr.mvvmsampleapp.util.show
import com.vibhu.nitjsr.mvvmsampleapp.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel =  ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        Log.d("####","LoginActivity ka onStarted says hello")
        //progress_bar.visibility = View.VISIBLE   ->  not so better way
        progress_bar.show() // --> better way to use extension function
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        Log.d("####","LoginActivity ka onSuccess says hello")
        loginResponse.observe(this, Observer{
            toast(it);//jo observer hai woh "it" naam ka variavble return karega
            progress_bar.hide() // -->  better way
        })
    }

    override fun onFailure(message: String) {
        Log.d("####","LoginActivity ka onFailure says hello")
        progress_bar.hide() //  --> better way
        toast("Login Failure  : $message")
    }
}