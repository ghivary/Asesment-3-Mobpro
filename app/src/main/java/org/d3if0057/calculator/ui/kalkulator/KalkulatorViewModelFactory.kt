package org.d3if0057.calculator.ui.kalkulator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0057.calculator.db.KalkulatorDao

class KalkulatorViewModelFactory(private val db: KalkulatorDao) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KalkulatorViewModel::class.java)) {
            return KalkulatorViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}