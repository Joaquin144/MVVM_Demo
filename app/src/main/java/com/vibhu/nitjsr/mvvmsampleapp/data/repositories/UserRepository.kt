package com.vibhu.nitjsr.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vibhu.nitjsr.mvvmsampleapp.data.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//ViewModel is class ko bolega login karne ko :--> same kaam hum log wahan par bhi kar sakte the No issue with that except ki woh MVVM rule ko violate karta(Rule states that Activity sirf ViewModel ko order dega and ViewModel userRepository ko order dega)
class UserRepository {
    fun userLogin(email: String, password: String): LiveData<String>{
        val loginResponse = MutableLiveData<String>()//We can't create instance of LiveData because it is abstract class(private constructor)
        MyApi().userLogin(email,password)//It's a bad practice but abhi kaam chalane ke liye kar diye hain --> Good practice is to inject it using dependency injection
            .enqueue(object: Callback<ResponseBody>{
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.isSuccessful){
                        loginResponse.value = response.body()?.toString()
                    }else{
                        loginResponse.value = response.errorBody()?.toString()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

            })
        return loginResponse  //<ig> it is implicit type casting
    }
}