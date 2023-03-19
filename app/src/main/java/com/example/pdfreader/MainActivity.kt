package com.example.pdfreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pdfreader.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTabLayout()
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewPager.apply {
            adapter = ViewPagerAdapter(supportFragmentManager, binding.tabLayout.tabCount)
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        }
    }

    private fun setupTabLayout() {
        binding.tabLayout.apply {
            addTab(this.newTab().setText("Home"))
            addTab(this.newTab().setText("Recent"))
            addTab(this.newTab().setText("Favourite"))

            // tabGravity = TabLayout.GRAVITY_FILL

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position?.let {
                        binding.viewPager.currentItem = it
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }
}
