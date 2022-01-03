package com.vibhu.nitjsr.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vibhu.nitjsr.mvvmsampleapp.data.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    fun userLogin(email: String, password: String): LiveData<String>{
        val loginResponse = MutableLiveData<String>()
        MyApi().userLogin(email,password)
            .enqueue(object: Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}