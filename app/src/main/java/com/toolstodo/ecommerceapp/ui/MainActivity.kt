package com.toolstodo.ecommerceapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.toolstodo.ecommerceapp.R
import com.toolstodo.ecommerceapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initNavigation()
    }

    private fun initNavigation() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.frgContainerView) as NavHostFragment
        navController = navHost.navController

        binding.bnvMainMenu.apply {
            navController.let { navController ->
                NavigationUI.setupWithNavController(
                    this,
                    navController
                )

                setOnItemSelectedListener { item ->
                    NavigationUI.onNavDestinationSelected(item, navController)
                    true
                }

                setOnItemReselectedListener {
                    val selected = navController.graph.findNode(it.itemId) as NavGraph

                    selected.let { navGraph ->
                        navController.popBackStack(navGraph.startDestinationId, false)
                    }
                }

            }
        }
    }

}