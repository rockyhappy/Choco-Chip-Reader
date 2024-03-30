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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanScreenViewModel @Inject constructor(
    private val sharedViewModel: SharedViewModel,
    private  var dataStore: DataStore<Preferences>
) : ViewModel(){
    fun onScanRecieved(barcodeInfo : String){

        viewModelScope.launch{
            try{
                val response = RetrofitInstance2.getApiServiceWithToken(dataStore).getDetails(barcodeInfo)
                if(response.isSuccessful){
                    val data = response.body()
                    if(data!=null){
                        sharedViewModel.setData(data)
                        Log.d("data",data.toString())
                    }
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}