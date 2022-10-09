package com.hammad.riyadh.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.hammad.riyadh.R
import com.hammad.riyadh.RiyadhApp
import com.hammad.riyadh.databinding.ActivityMainBinding
import com.hammad.riyadh.di.AppModule

class MainActivity : AppCompatActivity() {

    private lateinit var controller: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RiyadhApp.get().appComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigationListener()
    }

    private fun initNavigationListener() {
        val host = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        controller = host.navController
    }

    override fun onResume() {
        super.onResume()


    }
}