package org.d3if0057.calculator.ui.about

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0057.calculator.databinding.FragmentAboutBinding
import org.d3if0057.calculator.network.ApiStatus
import org.d3if0057.calculator.network.CalculatorApi

class AboutViewModel: ViewModel() {

    val copyright = MutableLiveData<String>()
    val status = MutableLiveData<ApiStatus>()


    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                val result = CalculatorApi.service.getCopyright()
                Log.d("MainViewModel", "Success: $result")
                copyright.postValue(result)
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): MutableLiveData<String> = copyright
    fun getStatus(): LiveData<ApiStatus> = status

}