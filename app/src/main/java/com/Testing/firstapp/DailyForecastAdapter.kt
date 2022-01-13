package com.Testing.firstapp

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text

class DailyForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tempText = view.findViewById<TextView>(R.id.tempText)
    private val descriptionText: TextView = view.findViewById(R.id.descriptionText)

    fun bind(dailyForecast: DailyForecast) {
        tempText.text =String.format("%.0f" ,dailyForecast.temp)
        descriptionText.text= dailyForecast.description
    }
}

class DailyForecastAdapter(
    private val clickHandler: (DailyForecast)-> Unit)
    : ListAdapter<DailyForecast,DailyForecastViewHolder>(DIFF_CONFIG) {

    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<DailyForecast>() {
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
               return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast , parent, false)
        return DailyForecastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
    }
}