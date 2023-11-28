package com.amineaytac.myfoodieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.amineaytac.myfoodieapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.itemActiveIndicatorColor = null

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainConatiner) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController)

        navController.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id){
                R.id.splashFragment -> {
                    binding.bottomNavigationView.gone()
                }
                R.id.favoriteFragment->{
                    binding.bottomNavigationView.visible()
                }
                R.id.homeFragment -> {
                    binding.bottomNavigationView.visible()
                }
                R.id.mealHomeFragment -> {
                    binding.bottomNavigationView.gone()
                }
                else -> {
                    binding.bottomNavigationView.visible()
                }
            }
        }
    }
}