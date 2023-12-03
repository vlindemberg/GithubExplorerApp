package com.vinicius.githubexplorerapp.presentation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.vinicius.githubexplorerapp.R
import com.vinicius.githubexplorerapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        setupNavMenu(navController)
    }

    private fun setupNavMenu(navigationController: NavController) {
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.authenticationFragment) {
                binding.bottomNavMenu.visibility = View.GONE
            } else {
                binding.bottomNavMenu.visibility = View.VISIBLE
            }
        }
        setupBottomNavMenuListener()
    }

    private fun setupBottomNavMenuListener() {
        binding.bottomNavMenu.setOnItemSelectedListener {
            setupFragmentsNavigation(it)
        }
    }

    private fun setupFragmentsNavigation(it: MenuItem): Boolean {
        when (it.itemId) {
            R.id.following -> navController.navigate(R.id.followingFragment)
            R.id.repositories -> navController.navigate(R.id.myRepositoriesFragment)
            R.id.profile -> navController.navigate(R.id.profileFragment)
            else -> {}
        }
        return true
    }
}
