package com.devrachit.chocochipreader.ui.authScreens.loginScreen

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.chocochipreader.Models.LoginRequest
import com.devrachit.chocochipreader.Models.SharedViewModel
import com.devrachit.chocochipreader.network.RetrofitInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val sharedViewModel: SharedViewModel,
    private val dataStore: DataStore<Preferences>
) : ViewModel(){

    private val _loading = MutableStateFlow(false)
    val loading= _loading.asStateFlow()

    private val _loginComplete = MutableStateFlow(false)
    val loginComplete= _loginComplete.asStateFlow()


    fun login(email:String,password:String){
        _loading.value=true
        val loginRequest= LoginRequest(
            username = email,
            password = password)
        viewModelScope.launch {
            try{
                val response = RetrofitInstance.apiService.login(email,password)
                if(response.isSuccessful){
                    val token = response.body()?.access
                    if(token!=null){
                        save("token",token)
//                        Log.d("token",token)
//                        sharedViewModel.setToken(token)
                        _loginComplete.value=true
                    }
                }
                else{
//                    sharedViewModel.setErrorMessage(response.message())
                }
                _loading.value=false

            }
            catch(e:Exception){
//                sharedViewModel.setErrorMessage(e.message.toString())
            }
        }
    }

    private suspend fun save (key:String , value:String){
        val dataStoreKey= preferencesKey<String>(key)
        dataStore.edit{temp ->
            temp[dataStoreKey]=value
        }
    }
}
