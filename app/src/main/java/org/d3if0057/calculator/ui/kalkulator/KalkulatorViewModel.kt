package org.d3if0057.calculator.ui.kalkulator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0057.calculator.db.KalkulatorDao
import org.d3if0057.calculator.db.KalkulatorEntity
import org.mariuszgromada.math.mxparser.Expression

class KalkulatorViewModel(private val db: KalkulatorDao) : ViewModel() {

    val data = db.getLastKalkulator()

    fun calculate(data: String): Double {
        val expression = getInputExpression(data)
        val result = Expression(expression).calculate()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataKalkulator = KalkulatorEntity(
                    input = data,
                    output = result.toString()
                )
                db.insert(dataKalkulator)
            }
        }

        return result
    }

    private fun getInputExpression(data: String): String {
        var expression = data.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }
}

