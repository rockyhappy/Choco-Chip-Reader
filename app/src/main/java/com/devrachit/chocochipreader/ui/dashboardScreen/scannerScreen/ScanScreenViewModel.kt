package com.devrachit.chocochipreader.ui.dashboardScreen.scannerScreen

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.chocochipreader.Models.SharedViewModel
import com.devrachit.chocochipreader.network.RetrofitInstance2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanScreenViewModel @Inject constructor(
     val sharedViewModel: SharedViewModel,
    private  var dataStore: DataStore<Preferences>
) : ViewModel(){

    private val _loading = MutableStateFlow(false)
    val loading= _loading.asStateFlow()

    private val _scanComplete = MutableStateFlow(false)
    val scanComplete= _scanComplete.asStateFlow()

     var _scanSuccess = MutableStateFlow(false)
    var scanSuccess= _scanSuccess.asStateFlow()
    fun onScanRecieved(barcodeInfo : String){

        viewModelScope.launch{
            try{
                _loading.value = true
                val response = RetrofitInstance2
                    .getApiServiceWithToken(dataStore)
                    .getDetails(barcodeInfo)
                if(response.isSuccessful){
                    val data = response.body()
                    if(data!=null){
                        sharedViewModel.setData(data)
                        Log.d("data",data.toString())
                        _scanSuccess.value = true
                    }
                }

                _loading.value = false
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun markPresent(day:String)
    {
        Log.d("markPresent","called")
        viewModelScope.launch{
            try{
                _loading.value = true
                val response = RetrofitInstance2
                    .getApiServiceWithToken(dataStore)
                    .markPresent(
//                        sharedViewModel.data.value!!.student_number,
                        student_number = 22153019,
                        day="day1"
                    )
                Log.d("markPresent",response.toString())
                if(response.isSuccessful){
                    Log.d("markPresent","success")
                    _scanComplete.value = true
                }

                _loading.value = false
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}