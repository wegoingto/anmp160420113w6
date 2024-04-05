package com.example.anmp_160420113_week4.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_160420113_week4.R
import com.example.anmp_160420113_week4.model.CarListAdapter
import com.example.anmp_160420113_week4.viewmodel.CarListViewModel

class CarListFragment : Fragment() {
    private val carListViewModel: CarListViewModel by viewModels()
    private val carListAdapter = CarListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_car_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recyclerViewAircraft).apply {
            adapter = carListAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        carListViewModel.cars.observe(viewLifecycleOwner) {car ->
            Log.d("ObserveViewModel.Car", "size: ${car.size}")
            carListAdapter.updateData(car)
        }

        carListViewModel.refresh()
    }
}