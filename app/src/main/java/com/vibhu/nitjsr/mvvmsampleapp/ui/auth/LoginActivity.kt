package com.vibhu.nitjsr.mvvmsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        //progress_bar.visibility = View.VISIBLE   ->  chote log
        progress_bar.show() // --> bade log
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        progress_bar.hide() // -->  bade log
        loginResponse.observe(this, Observer{
            toast(it);//jo observer hai woh "it" naam ka variavble return karega
        })
    }

    override fun onFailure(message: String) {
        progress_bar.hide() //  --> bade log
        toast("Login Failure  : $message")
    }
}