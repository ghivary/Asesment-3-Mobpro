package org.d3if0057.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import org.d3if0057.calculator.util.MyTimer

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var myTimer: MyTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        myTimer = MyTimer()
        Log.i("MainActivity", "onCreate dipanggil.")

    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart dipanggil.")
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onDestroy() {
        Log.i("MainActivity", "onDestroy dipanggil.")
        super.onDestroy()
    }


}