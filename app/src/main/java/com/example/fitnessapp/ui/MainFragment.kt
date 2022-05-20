package com.example.fitnessapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fitnessapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController =
            (childFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment).navController
        bottomNavView.setupWithNavController(navController)
    }
}