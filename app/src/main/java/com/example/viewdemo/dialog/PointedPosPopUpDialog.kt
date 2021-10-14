package com.example.viewdemo.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewdemo.R
import com.example.viewdemo.adapter.DialogAdapter

/**
 *<pre>
 *   author: wangjishun
 *   time: 2021/09/24
 *   desc:
 *</pre>
 **/
class PointedPosPopUpDialog(ct: Context) : Dialog(ct) {
    init {
        setContentView(R.layout.layout_pop_up_dialog)
        window?.setGravity(Gravity.BOTTOM.or(Gravity.CENTER))
        val displayMetrics = ct.resources.displayMetrics
        window?.setLayout(displayMetrics.widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setContentStr(strList: MutableList<String>) {
        val dialogRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_dialog_content)
        val adapter = DialogAdapter(strList)
        dialogRecyclerView.layoutManager = LinearLayoutManager(context)
        dialogRecyclerView.adapter = adapter
    }
}