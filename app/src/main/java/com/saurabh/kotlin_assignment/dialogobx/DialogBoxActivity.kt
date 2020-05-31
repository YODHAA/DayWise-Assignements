package com.saurabh.kotlin_assignment.dialogobx

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.saurabh.kotlin_assignment.R
import kotlinx.android.synthetic.main.dialog_screen.*

class DialogBoxActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_screen)

        showAlertID.setOnClickListener {
            showAlert()
        }

        check.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener{
            compoundButton , b -> check.setText(
            if (compoundButton.isChecked) " CheckBox Checked " else "CheckBox UnChecked"
        )
        })

    }

    private fun showAlert() {

        var myDialog:AlertDialog?=null

        val myBuilder=AlertDialog.Builder(this)

        myBuilder.setPositiveButton("OK") {
            dialogInterface, i -> Toast.makeText(this@DialogBoxActivity , " OK Clicked ",Toast.LENGTH_LONG).show()
        }

        myBuilder.setNegativeButton("Cancel") {
                dialogInterface, i -> Toast.makeText(this@DialogBoxActivity, "Cancel Clicked ",Toast.LENGTH_LONG).show()
        }

        myBuilder.setTitle("Saurabh Tips ").setMessage("Saurabh's Kotlin Tutorials List. ")

        myDialog=myBuilder.create()

        myDialog!!.show()


    }
}