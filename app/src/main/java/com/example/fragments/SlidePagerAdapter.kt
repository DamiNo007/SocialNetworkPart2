package com.example.fragments

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

class SlidePagerAdapter(
    fragmentManager: FragmentManager?,
    private val fragmentList: List<Fragment>
) :
    FragmentPagerAdapter(fragmentManager!!) {
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}