package com.example.testapplication1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity(){
    //lateinit var mViewModel: MainViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this,R.id.nav_host)
        //supportFragmentManager.beginTransaction().replace(R.id.nav_container, fragment1()).commit()
        /*mViewModel = ViewModelProvider(this).get(MainViewModel:: class.java)
        mViewModel.title.observe(this, Observer {
            что то делается
        })*/

    }

    /*private fun openFrag(f: Fragment) {
        supportFragmentManager
            .beginTransaction().replace(R.id.nav_container, f)
            .commit()
    }*/
}