package com.example.anmp_160420113_week4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmp_160420113_week4.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StudentListViewModel(application: Application): AndroidViewModel(application){
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private val VOLLEY_TAG = "volley"
    private var queue: RequestQueue? = null


    fun refresh() {
        loadingLD.value = true
        studentLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            "http://adv.jitusolution.com/student.php",
            { response ->
                Log.d("VolleyRequest", response)
                val students = Gson().fromJson<List<Student>>(
                    response,
                    object : TypeToken<List<Student>>() {}.type,
                )
                this.studentsLD.value = students as ArrayList<Student>
                loadingLD.value = false
            },
            { error ->
                Log.d("VolleyRequest", error.message.toString())
                loadingLD.value = false
                studentLoadErrorLD.value = true
            },
        )
        stringRequest.tag = VOLLEY_TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(VOLLEY_TAG)
    }
}