package org.d3if0057.calculator.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KalkulatorDao {
    @Insert
    fun insert(kalkulator: KalkulatorEntity)
    @Query("SELECT * FROM kalkulator ORDER BY id DESC")
    fun getLastKalkulator(): LiveData<List<KalkulatorEntity>>
    @Query("DELETE FROM kalkulator")
    fun clearData()
    @Query("Delete from kalkulator where id = :id")
    fun clearColumn(id: Int)

}