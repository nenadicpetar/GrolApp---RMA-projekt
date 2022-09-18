package com.example.grolapp2.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
const val KAUF = "Kaufland"
const val LIDL = "Lidl"
const val SPAR = "Spar"
const val KONZ = "Konzum"

class CustomPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragments: MutableList<Fragment> = mutableListOf()

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> KAUF
            1 -> LIDL
            2 -> SPAR
            3 -> KONZ
            else -> null
        }
    }
}