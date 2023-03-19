package com.example.pdfreader

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.pdfreader.Fragments.FavouriteFragment
import com.example.pdfreader.Fragments.HomeFragment
import com.example.pdfreader.Fragments.RecentFragment


class ViewPagerAdapter(fm: FragmentManager, var tabCount: Int) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> HomeFragment()
                1 -> RecentFragment()
                2 -> FavouriteFragment()
                else -> HomeFragment()
            }
        }

        override fun getCount(): Int {
            return tabCount
        }

        override fun getPageTitle(position: Int): CharSequence {
            return "Tab " + (position + 1)
        }
    }

