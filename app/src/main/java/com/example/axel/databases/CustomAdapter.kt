package com.example.axel.databases

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.student_row_model.view.*

/**
 * Created by Axel on 04-03-2018.
 */
class CustomAdapter(val studentList: ArrayList<Student>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val studentRow = LayoutInflater.from(parent!!.context).inflate(R.layout.student_row_model, parent, false)
        return ViewHolder(studentRow)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val currentStudent = studentList.get(position)
        holder!!.itemView!!.textView_name.text = currentStudent.name
        holder.itemView.textView_section.text = currentStudent.section
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}