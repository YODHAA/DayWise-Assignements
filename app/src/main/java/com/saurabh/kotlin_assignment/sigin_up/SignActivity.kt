package com.saurabh.kotlin_assignment.sigin_up

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.saurabh.kotlin_assignment.R
import kotlinx.android.synthetic.main.login_screen.*
import kotlinx.android.synthetic.main.sign_screen.*
import kotlinx.android.synthetic.main.userreg_screen.*

class SignActivity : AppCompatActivity() {

    lateinit var handler:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_screen)

        handler= DatabaseHelper(this)
        showHome()

        registration.setOnClickListener{
           showRegister()
        }

        login.setOnClickListener {
            showLogin()
        }

        save.setOnClickListener {

            handler.insertUserData(name.text.toString(),email.text.toString(),password_register.text.toString())
            showHome()
        }

        login_button.setOnClickListener {

            if(handler.userpresent(login_email.text.toString(),login_password.text.toString())){

                Toast.makeText(this,"Login Successful",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Invalid User !!!!",Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun showLogin() {
        registration_layout.visibility=View.GONE
        login_layout.visibility=View.VISIBLE
        home_l1.visibility=View.GONE
    }

    private fun showRegister() {
        registration_layout.visibility=View.VISIBLE
        login_layout.visibility=View.GONE
        home_l1.visibility=View.GONE
    }

    private fun showHome() {
        registration_layout.visibility=View.GONE
        login_layout.visibility=View.GONE
        home_l1.visibility=View.VISIBLE
    }
}