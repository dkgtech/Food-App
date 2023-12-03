package com.dkgtech.foodapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.dkgtech.foodapp.R
import com.dkgtech.foodapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setting up bottom navigation

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val navController = Navigation.findNavController(this, R.id.fragmentContainer)
        NavigationUI.setupWithNavController(bottomNavigation, navController)

    }
}