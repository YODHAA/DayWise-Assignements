package com.saurabh.kotlin_assignment.kotlinactivities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.saurabh.kotlin_assignment.R
import android.content.Intent
import kotlinx.android.synthetic.main.kotlinactivity_screen.*
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.content.res.Configuration
import android.util.Log
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat
import java.util.*

class KotlinActivity:AppCompatActivity() {
    // 1
    private val PREFS_TASKS = "prefs_tasks"
    private val KEY_TASKS_LIST = "tasks_list"
    private val ADD_TASK_REQUEST = 1
    private val taskList: MutableList<String> = mutableListOf()
    private val adapter by lazy { makeAdapter(taskList) }
    private val tickReceiver by lazy { makeBroadcastReceiver() }

    companion object {
        private const val TAG = "KotlinActivity"

        private fun getCurrentTimeStamp(): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
            val now = Date()
            return sdf.format(now)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        // 2
        super.onCreate(savedInstanceState)
        // 3
        setContentView(R.layout.kotlinactivity_screen)
                // 4
        taskListView.adapter = adapter

        // 5
        taskListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            taskSelected(position)
        }

        val savedList = getSharedPreferences(PREFS_TASKS, Context.MODE_PRIVATE).getString(KEY_TASKS_LIST, null)
        if (savedList != null) {
            val items = savedList.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            taskList.addAll(items)
        }

        addTaskButton.setOnClickListener {
            addTaskClicked()
        }
    }

    override fun onResume() {
        super.onResume()

        dateTimeTextView.text = getCurrentTimeStamp()

        registerReceiver(tickReceiver, IntentFilter(Intent.ACTION_TIME_TICK))
    }

    override fun onPause() {
        super.onPause()

        try {
            unregisterReceiver(tickReceiver)
        } catch (e: IllegalArgumentException) {
            Log.e(KotlinActivity.TAG, "Time tick Receiver not registered", e)
        }
    }

    override fun onStop() {
        super.onStop()

        // Save all data which you want to persist.
        val savedList = StringBuilder()
        for (task in taskList) {
            savedList.append(task)
            savedList.append(",")
        }

        getSharedPreferences(PREFS_TASKS, Context.MODE_PRIVATE).edit()
            .putString(KEY_TASKS_LIST, savedList.toString()).apply()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 1
        if (requestCode == ADD_TASK_REQUEST) {
            // 2
            if (resultCode == Activity.RESULT_OK) {
                // 3
                val task = data?.getStringExtra(TaskDescriptionActivity.EXTRA_TASK_DESCRIPTION)
                task?.let {
                    taskList.add(task)
                    // 4
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

//    You can start an activity with either startActivity() or startActivityForResult(). They are similar except that
//    startActivityForResult() will result in onActivityResult() being called once the TaskDescriptionActivity finishes.
    // 6
    fun addTaskClicked() {
        val intent = Intent(this, TaskDescriptionActivity::class.java)
        startActivityForResult(intent, ADD_TASK_REQUEST)
    }

    // 7
    private fun makeAdapter(list: List<String>): ArrayAdapter<String> =
        ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

    private fun makeBroadcastReceiver(): BroadcastReceiver {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                if (intent?.action == Intent.ACTION_TIME_TICK) {
                    dateTimeTextView.text = getCurrentTimeStamp()
                }
            }
        }
    }

    private fun taskSelected(position: Int) {

        AlertDialog.Builder(this@KotlinActivity)
            .setTitle("Task")
            .setMessage(taskList[position])
            .setPositiveButton("Delete") { _, _ ->
                taskList.removeAt(position)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", { dialog, _ -> dialog.cancel() })
            .create()
            .show()
    }


}