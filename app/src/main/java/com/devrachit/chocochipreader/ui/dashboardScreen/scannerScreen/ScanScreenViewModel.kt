package com.devrachit.chocochipreader.ui.dashboardScreen.scannerScreen

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.chocochipreader.Models.MarkPresentRequest
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
    private var dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

     var _scanComplete = MutableStateFlow(false)
    var scanComplete = _scanComplete.asStateFlow()

    var _scanSuccess = MutableStateFlow(false)
    var scanSuccess = _scanSuccess.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private val _error = MutableStateFlow(false)
    val error = _error.asStateFlow()

    private val _lastApiCall = MutableStateFlow(0)
    val lastApiCall = _lastApiCall.asStateFlow()

    fun onScanComplete() {
        _scanComplete.value = false
    }
    fun onScanSuccess() {
        _scanSuccess.value = false
    }
    fun onError() {
        _error.value = false
    }
    fun onScanRecieved(barcodeInfo: String) {

        viewModelScope.launch {
            try {
                _loading.value = true
                val response = RetrofitInstance2
                    .getApiServiceWithToken(dataStore)
                    .getDetails(barcodeInfo)
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        sharedViewModel.setData(data)
                        Log.d("data", data.toString())
                        _scanSuccess.value = true
                    }
                }
                else{
                    when(response.code()){
                        404 -> _errorMessage.value = "No Student matches the given query"
                        else -> _errorMessage.value = response.errorBody().toString()
                    }
                    _error.value = true
                }

                _loading.value = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun markPresent( day: String) {
        Log.d("markPresent", "called")
        viewModelScope.launch {
            try {
                val request = MarkPresentRequest(
                    student_number=sharedViewModel.data.value!!.student_number,
                    day=day,
                )
                _loading.value = true
                val response = RetrofitInstance2
                    .getApiServiceWithToken(dataStore)
                    .markPresent(request)
                Log.d("markPresent", response.toString())
                if (response.isSuccessful) {
                    Log.d("markPresent", "success")
                    _scanComplete.value = true
                    _lastApiCall.value = 1
                }
                else
                {
                    when(response.code()){
                        404 -> _errorMessage.value = "No Student matches the given query"
                        400-> _errorMessage.value = "Day is not active "
                        else -> _errorMessage.value = response.errorBody().toString()
                    }
                    _error.value = true
                    //_errorMessage.value = response.errorBody().toString()
                }

                _loading.value = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun unmarkPresent( day: String) {
        Log.d("markPresent", "called")
        viewModelScope.launch {
            try {
                val request = MarkPresentRequest(
                    student_number=sharedViewModel.data.value!!.student_number,
                    day=day,
                )
                _loading.value = true
                val response = RetrofitInstance2
                    .getApiServiceWithToken(dataStore)
                    .unmarkPresent(request)
                Log.d("unmarkPresent", response.toString())
                if (response.isSuccessful) {
                    Log.d("unmarkPresent", "success")
                    _scanComplete.value = true
                    _lastApiCall.value = 2
                }
                else
                {
                    when(response.code()){
                        404 -> _errorMessage.value = "No Student matches the given query"
                        400-> _errorMessage.value = "Day is not active "
                        else -> _errorMessage.value = response.errorBody().toString()
                    }
                    _error.value = true
                    //_errorMessage.value = response.errorBody().toString()
                }

                _loading.value = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}