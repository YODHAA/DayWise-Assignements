package com.saurabh.kotlin_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        excellent.setOnClickListener {
            var msg="You have rated the Payment Process as : Excellent "
            texttoast.text=msg
            sad.isChecked=false
            good.isChecked=false

        }

        sad.setOnClickListener {
            var msg="You have rated the Payment Process as : Boring "
            texttoast.text=msg
            excellent.isChecked=false
            good.isChecked=false
        }

        good.setOnClickListener {
            var msg="You have rated the Payment Process as : Good  "
            texttoast.text=msg
            sad.isChecked=false
            excellent.isChecked=false
        }

    }
}
