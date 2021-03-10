package com.adib.eurekatest

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class ViewPagerAdapter(var context: Context, fragmentManager: FragmentManager, private var totalTabs: Int, private val items: ArrayList<Question>):
    FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        val item = items[position]
        return when (position) {
            0 -> {
                FirstQuestion(item)
            }
            1 -> {
                SecondQuestion(item)
            }
            2 -> {
                ThirdQuestion(item)
            }
            3 -> {
                FourthQuestion(item)
            }
            4 -> {
                FifthQuestion(item)
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int = totalTabs
}