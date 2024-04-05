package com.example.anmp_160420113_week4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.anmp_160420113_week4.model.Car

class CarListViewModel(
    app: Application,
) : AndroidViewModel(app) {
    val cars = MutableLiveData<List<Car>>()

    private val VOLLEY_TAG = "volley.car"
    private var queue: RequestQueue? = null

    fun refresh() {
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            "http://10.0.2.2/ANMP/cars.json",
            { response ->
                Log.d("VolleyRequest.Car", response)
                this.cars.value = Gson().fromJson<List<Car>>(
                    response,
                    object : TypeToken<List<Car>>() {}.type,
                )
            },
            { error ->
                Log.d("VolleyRequest.Car", error.message.toString())
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