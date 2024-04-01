package com.devrachit.chocochipreader.ui.dashboardScreen.ListScreen

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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
class ListScreenViewModel@Inject constructor(
    val sharedViewModel: SharedViewModel,
    private var dataStore: DataStore<Preferences>
): ViewModel(){

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _apiCallMade = MutableStateFlow(false)
    val apiCallMade = _apiCallMade.asStateFlow()
    fun checkAttendance(day: String){
        viewModelScope.launch{
            _loading.value = true
            try{
                val response = RetrofitInstance2
                    .getApiServiceWithToken(dataStore)
                    .checkAttendance(day)
                if(response.isSuccessful){
                    val data = response.body()
                    if(data != null){
                        sharedViewModel.setDataList(data)
                        _apiCallMade.value = true
                    }
                }
                _loading.value = false
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }

    }

}