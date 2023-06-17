package org.d3if0057.calculator.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0057.calculator.db.KalkulatorDao

class HistoriViewModel(private val db: KalkulatorDao) : ViewModel() {
    val data = db.getLastKalkulator()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }

}