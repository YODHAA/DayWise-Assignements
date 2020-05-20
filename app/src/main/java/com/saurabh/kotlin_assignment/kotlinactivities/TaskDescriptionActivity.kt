package com.saurabh.kotlin_assignment.kotlinactivities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.saurabh.kotlin_assignment.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import kotlinx.android.synthetic.main.activity_task_description.*

class TaskDescriptionActivity :AppCompatActivity() {
//    companion object for the class to define attributes common across the class, similar to static members in Java.
    companion object {
        val EXTRA_TASK_DESCRIPTION = "task"
    fun newIntent(context: Context) = Intent(context, TaskDescriptionActivity::class.java)

    fun getNewTask(data: Intent?): String? = data?.getStringExtra(TaskDescriptionActivity.EXTRA_TASK_DESCRIPTION)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_description)

    }

    //Added an empty click handler that will be used to finish the activity.
    fun doneClicked(view: View) {
        // 1
        val taskDescription = descriptionText.text.toString()

        if (!taskDescription.isEmpty()) {
            // 2
            val result = Intent()
            result.putExtra(EXTRA_TASK_DESCRIPTION, taskDescription)
            setResult(Activity.RESULT_OK, result)
        } else {
            // 3
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }



}