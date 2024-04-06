package com.devrachit.chocochipreader.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveToDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
): ViewModel(){
    suspend fun save(key: String, value: String) {
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { temp ->
            temp[dataStoreKey] = value
        }
    }
}
