package com.saurabh.kotlin_assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saurabh.kotlin_assignment.R
import com.saurabh.kotlin_assignment.dataclass.Post
import com.saurabh.kotlin_assignment.dataclass.PostItem
import kotlinx.android.synthetic.main.post_item_layout.view.*

class PostItemAdapter(val postList: List<Post>, val context: Context) :
    RecyclerView.Adapter<PostItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.post_item_layout,
            parent, false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.txtPostTitle.text = postList.get(position).title
        holder.itemView.txtPostBody.text = postList.get(position).body

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}