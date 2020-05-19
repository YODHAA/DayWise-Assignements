package com.saurabh.kotlin_assignment.customcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.saurabh.kotlin_assignment.R
import kotlinx.android.synthetic.main.customcomponent_screen.view.*

class CustomComponent constructor(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.customcomponent_screen, this, true)
        orientation = VERTICAL

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.custom_component_attributes, 0, 0)
            val title = resources.getText(typedArray
                .getResourceId(R.styleable.custom_component_attributes_custom_component_title, R.string.component_one))

            my_title.text = title
            my_edit.hint = "${resources.getString(R.string.hint_text)} $title"

            typedArray.recycle()
        }
    }

}