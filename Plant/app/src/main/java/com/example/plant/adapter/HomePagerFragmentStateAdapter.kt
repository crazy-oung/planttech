package com.example.plant.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.plant.Fragment.*

class HomePagerFragmentStateAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    var fragments : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AllFragment()
            1 -> StarFragment()
            2 -> FruitFragment()
            3 -> VegitableFragment()
            else -> OrnamentalFragment()
        }
    }

    fun addFragment(fragment: Fragment){
        fragments.add(fragment)
        notifyItemInserted(fragments.size - 1)
    }
}