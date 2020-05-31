package com.saurabh.kotlin_assignment.apicall

import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.saurabh.kotlin_assignment.R

class CardActivity:AppCompatActivity() {

    private var jsonURL = "https://jsonplaceholder.typicode.com/users"
   // private var jsonURL = "https://attendence-call.s3.ap-south-1.amazonaws.com/ApiCall/people_call.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_main)

        val gv = findViewById<GridView>(R.id.myGridView)

        val downloadBtn = findViewById<Button>(R.id.downloadBtn)
        downloadBtn.setOnClickListener({ ApiDownloader(this@CardActivity, jsonURL, gv).execute() })
    }

}