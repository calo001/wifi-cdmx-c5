package com.github.wifigratuitoc5.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.github.wifigratuitoc5.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Actividad principal de la app
 *
 * Se encarga de mostrar el SplashScreen usando un AppTheme personalizado, configurar la
 * ActionBar y el BottomNavigationView para funcionar con el NavigationController
 */

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        navigationSetup()
    }

    /**
     * Configura la ActionBar y el BottomNavigationView para funcionar con el NavigationController
     */
    private fun navigationSetup() {
        appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home))
        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    /**
     * Configura el comportamiento del boton de Subir navegacion
     * @return Utiliza el objeto NavigationUI para controlar el comportamiento
     */
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    /**
     * Configura el menu de la app
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Configura el comportamiento al seleccionar un item de menu usando el objeto NavigationUI
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        NavigationUI.onNavDestinationSelected(item, navController)
        return super.onOptionsItemSelected(item)
    }
}
