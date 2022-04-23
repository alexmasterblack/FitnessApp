package com.example.fitnessapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ActivityFragment : Fragment(R.layout.fragment_activity) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val pager = view.findViewById<ViewPager2>(R.id.pager)

        val adapter = ViewPagerAdapter(this)

        pager.adapter = adapter
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Моя"
                }
                1 -> {
                    tab.text = "Пользователей"
                }
            }
        }.attach()
    }
}