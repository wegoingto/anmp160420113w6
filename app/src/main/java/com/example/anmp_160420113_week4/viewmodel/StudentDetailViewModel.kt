package com.example.anmp_160420113_week4.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.anmp_160420113_week4.model.Student

class StudentDetailViewModel(application: Application) : AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()

    fun fetch() {
        this.studentLD.value = Student(
            "16055",
            "Nonie",
            "1998/03/28",
            "5718444778",
            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
    }
}
