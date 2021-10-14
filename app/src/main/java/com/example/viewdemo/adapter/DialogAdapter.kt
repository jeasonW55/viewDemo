package com.example.viewdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewdemo.R

/**
 *<pre>
 *   author: wangjishun
 *   time: 2021/09/24
 *   desc:
 *</pre>
 **/

class DialogAdapter(contentStrList: MutableList<String>) :
        RecyclerView.Adapter<DialogAdapter.RCViewHolder>() {

    class RCViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var contentStrList: MutableList<String> = contentStrList
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RCViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.dialog_report_item, parent, false)
        return RCViewHolder(view)
    }

    override fun onBindViewHolder(holder: RCViewHolder, position: Int) {
        val size = contentStrList.size
        val itemContent = holder.itemView.findViewById<TextView>(R.id.tv_item_content)
        if (position < size && position > -1) {
            itemContent.text = contentStrList[position]
        }
    }

    override fun getItemCount(): Int {
        return contentStrList.size
    }
}