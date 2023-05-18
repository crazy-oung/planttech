package com.example.plant.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.plant.Fragment.*

class InfoMorePurchasePagerFragmentStateAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    var fragments : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> InfoPurchaseListOneFragment()
            1 -> InfoPurchaseListTwoFragment()
            else -> InfoPurchaseListThreeFragment()
        }
    }

    fun addFragment(fragment: Fragment){
        fragments.add(fragment)
        notifyItemInserted(fragments.size - 1)
    }
}