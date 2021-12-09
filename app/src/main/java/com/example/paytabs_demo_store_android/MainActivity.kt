package com.example.paytabs_demo_store_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.paytabs_demo_store_android.base_classes.hide
import com.example.paytabs_demo_store_android.base_classes.shouldShow
import com.example.paytabs_demo_store_android.base_classes.show
import com.example.paytabs_demo_store_android.database.dao.BagDao
import com.example.paytabs_demo_store_android.databinding.ActivityMainBinding
import com.example.paytabs_demo_store_android.onboarding.config.AppPrefs
import com.example.paytabs_demo_store_android.onboarding.view.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var bagDao: BagDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (AppPrefs(this).isFirstTimeLaunch()) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.incToolbar.tvToolbarTitle.text = getString(R.string.home_title)
        initBottomNavigationView()
        setContentView(binding.root)
        observeBagCounter()
        onClickActions()
    }

    private fun initBottomNavigationView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        navController.addOnDestinationChangedListener { controller, destination, arguments ->

            binding.incToolbar.root.show()
            binding.incToolbar.grpBagBadge.show()
            when (destination.id) {
                R.id.navigation_home -> {
                    binding.incToolbar.tvToolbarTitle.text = "Home"
                    binding.bvMain.show()
                }
                R.id.navigation_categories -> {
                    binding.incToolbar.tvToolbarTitle.text = "Categories"
                    binding.bvMain.show()
                }
                R.id.navigation_favourites -> {
                    binding.incToolbar.tvToolbarTitle.text = "Favourites"
                    binding.bvMain.show()
                }
                R.id.navigation_profile -> {
                    binding.incToolbar.tvToolbarTitle.text = "Profile"
                    binding.bvMain.show()
                }
                R.id.navigation_product_details -> {
                    binding.incToolbar.tvToolbarTitle.text = "Product Details"
                    binding.bvMain.show()
                }
                R.id.navigation_products -> {
                    binding.incToolbar.tvToolbarTitle.text = "Products"
                    binding.bvMain.show()
                }
                R.id.navigation_orders -> {
                    binding.incToolbar.tvToolbarTitle.text = "My Orders"
                    binding.bvMain.show()
                }
                R.id.navigation_bag -> {
                    binding.incToolbar.tvToolbarTitle.text = "Cart "
                    binding.bvMain.hide()
                    binding.incToolbar.grpBagBadge.hide()
                }
                else -> {
                    binding.bvMain.hide()
                }
            }
        }
        binding.bvMain.setupWithNavController(navController)
    }


    private fun onClickActions() {
        binding.incToolbar.ivBag.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.action_navigation_home_to_fragment_bag)
                }
                R.id.navigation_categories -> {
                    navController.navigate(R.id.action_navigation_categories_to_products_fragment)
                }
                R.id.navigation_favourites -> {
                    navController.navigate(R.id.action_navigation_favourites_to_fragment_bag)
                }
                R.id.navigation_profile -> {
                    navController.navigate(R.id.action_navigation_profile_to_fragment_bag)
                }
                R.id.navigation_products -> {
                    navController.navigate(R.id.action_products_fragment_to_fragment_bag)
                }
                R.id.navigation_product_details -> {
                    navController.navigate(R.id.action_product_details_fragment_to_fragment_bag)
                }
                R.id.navigation_orders -> {
                    navController.navigate(R.id.action_orders_fragment_to_fragment_bag)
                }
            }
        }
        binding.incToolbar.settingsIcon.setOnClickListener {
            navController.navigate(R.id.settingsFragment)
        }
    }

    private fun observeBagCounter() {
        bagDao.getBagCount().observe(this, {
            binding.incToolbar.tvBadge.shouldShow(it > 0)
            binding.incToolbar.tvBadge.text = it.toString()
        })
    }


}