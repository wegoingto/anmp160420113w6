package com.example.anmp_160420113_week4.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anmp_160420113_week4.R
import com.example.anmp_160420113_week4.databinding.StudentListItemBinding
import com.example.anmp_160420113_week4.view.StudentListFragmentDirections
import com.google.android.material.textfield.TextInputEditText

class StudentListAdapter(
    val students: ArrayList<Student>, ) : RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    class ViewHolder(var view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = StudentListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.student = students[position]
        Glide.with(holder.view.imageProfile.context)
            .load(students[position].photoUrl)
            .into(holder.view.imageProfile)
        holder.view.btnDetail.setOnClickListener {
            val student = students[position]
            val action = StudentListFragmentDirections.actionDetailFragment(student)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateStudent(newStudentList:ArrayList<Student>) {
        students.clear()
        students.addAll(newStudentList)
        notifyDataSetChanged()
    }
}