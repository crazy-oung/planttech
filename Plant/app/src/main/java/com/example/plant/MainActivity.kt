package com.example.plant

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.plant.Fragment.*
import com.example.plant.databinding.ActivityMainBinding
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val homeFragment = HomeFragment()
        val plantFragment = PlantFragment()
        val graphFragment = GraphFragment()
        val boardFragment = BoardFragment()
        val infoFragment = InfoFragment()

        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.first -> replaceFragment(homeFragment)
                R.id.second -> replaceFragment(plantFragment)
                R.id.third -> replaceFragment(graphFragment)
                R.id.fourth -> replaceFragment(boardFragment)
                R.id.fifth -> replaceFragment(infoFragment)
            }
            true
        }
        binding.bnvMain.selectedItemId = R.id.first
    }

    private fun replaceFragment(fragment: Fragment) {
        // 현 Activity 에 연결된 Fragment 관리하는 supportFragmentManager 를 통해 Fragment 전환
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }

}