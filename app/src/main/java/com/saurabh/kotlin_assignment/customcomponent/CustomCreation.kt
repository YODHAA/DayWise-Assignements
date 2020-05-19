package com.saurabh.kotlin_assignment.customcomponent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.saurabh.kotlin_assignment.R

class CustomCreation:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom)
    }
}