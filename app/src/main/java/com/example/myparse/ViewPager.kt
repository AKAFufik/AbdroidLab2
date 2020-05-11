package com.example.myparse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_viewpager.*

class ViewPager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        val previousImage = intent.extras!!.getString("previousImage")
        val currentImage = intent.extras!!.getString("currentImage")
        val nextImage = intent.extras!!.getString("nextImage")

        val previousName = intent.extras!!.getString("previousName")
        val currentName = intent.extras!!.getString("currentName")
        val nextName = intent.extras!!.getString("nextName")

        val previousDescription = intent.extras!!.getString("previousDescription")
        val currentDescription = intent.extras!!.getString("currentDescription")
        val nextDescription = intent.extras!!.getString("nextDescription")


        viewPagerAdapter.addFragment(ExampleFragment(previousImage!!, previousName!!, previousDescription!!), "one")
        viewPagerAdapter.addFragment(ExampleFragment(currentImage!!, currentName!!, currentDescription!!), "two")
        viewPagerAdapter.addFragment(ExampleFragment(nextImage!!, nextName!!, nextDescription!!), "three")

        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = 1
    }
}