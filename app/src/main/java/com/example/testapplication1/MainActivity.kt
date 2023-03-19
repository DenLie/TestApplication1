package com.example.testapplication1

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.nav_container, fragment1()).commit()
    }

    /*private fun openFrag(f: Fragment) {
        supportFragmentManager
            .beginTransaction().replace(R.id.nav_container, f)
            .commit()
    }*/
}