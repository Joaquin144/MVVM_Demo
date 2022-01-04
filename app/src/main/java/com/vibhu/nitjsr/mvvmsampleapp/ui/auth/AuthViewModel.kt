package com.vibhu.nitjsr.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.vibhu.nitjsr.mvvmsampleapp.data.repositories.UserRepository

class AuthViewModel : ViewModel() {

    var email: String? = null //if we don't put null safety then app will crash as soon as the Acvityi is inflated because at that time both ETs will be empty
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View){
        //authListener = null
        authListener?.onStarted() //Doubt(solved) : Jab null hoga authListener tab bhi kya onStarted() function run karega ?   --->Nahi null hone par method run nahi karega maine delibraytely null kiya aur Log.d kiya toh woh nahi chala. Ya toh koi class(in our app LoginActivity is initializeing authListener (agar object authlistener ko private kar diya toh error degi woh activity.)) authListener ko value de de warna null rhaa toh functions call nahi honge due to null safety feature of koltin. and agar authListener!! laga ke null par call karne ki zabardasti ki toh app crash hoga
        //Doubt(solved) :interface ke methods ko yahan override nahi kiya par LoginActivity mein kiya toh kya LoginActitiy wala overridden method chalega ? --> haan waqhi wale chal rahe hain
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Inavlid email or password")
            return
        }
        //else -> email and password entered by user are both Okay
        val loginResponse = UserRepository().userLogin(email!!,password!!)//To create an instance of repo in ViewModel is a bad practcie because in this way AuthViewModel will become dependent on UserRepository which means our app is tightly coupled. But for now let it be. Good practice is to use dependency injection
        authListener?.onSuccess(loginResponse)
    }
}