package com.example.anmp_160420113_week4.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_160420113_week4.R
import com.example.anmp_160420113_week4.view.StudentListFragmentDirections

class StudentListAdapter(
    val students: ArrayList<Student>, ) : RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtID: TextView
        val txtName: TextView
        val btnDetail: Button

        init {
            txtID = view.findViewById(R.id.textID)
            txtName = view.findViewById(R.id.txtName)
            btnDetail  = view.findViewById(R.id.btnDetail)

            //view.findViewById<Button>(R.id.btnDetail).setOnClickListener {
                //onButtonOpenDetailClick(adapterPosition)
            //}
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val txtID = holder.itemView.findViewById<TextView>(R.id.txtID)
        val txtName = holder.itemView.findViewById<TextView>(R.id.txtName)
        val btnDetail = holder.itemView.findViewById<Button>(R.id.btnDetail)

        txtID.text = students[position].id
        txtName.text = students[position].fullName
        btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun updateStudent(newStudentList:ArrayList<Student>) {
        students.clear()
        students.addAll(newStudentList)
        notifyDataSetChanged()
    }
}