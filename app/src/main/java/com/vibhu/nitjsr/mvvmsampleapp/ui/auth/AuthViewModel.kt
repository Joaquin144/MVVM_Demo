package com.vibhu.nitjsr.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.vibhu.nitjsr.mvvmsampleapp.data.repositories.UserRepository

class AuthViewModel : ViewModel() {

    var email: String? = null //if we don't put null safety then app will crash as soon as the Acvityi is inflated because at that time both ETs will be empty
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View){
        authListener?.onStarted() //Doubt : Jab null hoga authListener tab bhi kya onStarted() function run karega ?
        //Doubt -> interface ko yahan override nahi kiya par LoginActivity mein kiya toh kya LoginActitiy wala overridden method chalega ?
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Inavlid email or password")
            return
        }
        //else -> email and password entered by user are both Okay
        val loginResponse = UserRepository().userLogin(email!!,password!!)//To create an instance of repo in ViewModel is a bad practcie because in this way AuthViewModel will become dependent on UserRepository which means our app is tightly coupled. But for now let it be.
        authListener?.onSuccess(loginResponse)
    }
}