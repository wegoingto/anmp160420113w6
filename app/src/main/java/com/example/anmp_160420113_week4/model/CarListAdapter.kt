package com.example.anmp_160420113_week4.model

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_160420113_week4.R

class CarListAdapter(
    private val car: MutableList<Car> = mutableListOf(),
) : RecyclerView.Adapter<CarListAdapter.ViewHolder>() {
    private val numberFormat = NumberFormat.getNumberInstance()

    class ViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        val textCarModel: TextView
        val textCarYear: TextView
        val textCarFeatures: TextView
        val textCarSpec: TextView


        init {
            textCarModel = view.findViewById(R.id.textCarModel)
            textCarYear = view.findViewById(R.id.textCarYear)
            textCarFeatures= view.findViewById(R.id.textAircraftCountry)
            textCarSpec = view.findViewById(R.id.textEngine)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_car, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = car.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val car = car[position]

            textCarModel.text = car.model
            textCarYear.text = car.year
            textCarFeatures.text = car.features.toString()
            textCarSpec.text = car.specs.toString()
        }
    }

    fun updateData(data: List<Car>) {
        car.clear()
        car.addAll(data)
        notifyDataSetChanged()
    }
}