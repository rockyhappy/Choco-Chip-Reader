package com.devrachit.chocochipreader.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(

) : ViewModel(){
    private var token : String = ""
    private var errorMessage : String = ""

    private val _data= MutableLiveData<DetailsResponse>()
    var data: LiveData<DetailsResponse> = _data


    fun setData(data : DetailsResponse){
        _data.value = data
    }

    fun setToken(token : String){
        this.token = token
    }

    fun getToken() : String{
        return token
    }

    fun setErrorMessage(errorMessage : String){
        this.errorMessage = errorMessage
    }

    fun getErrorMessage() : String{
        return errorMessage
    }
}