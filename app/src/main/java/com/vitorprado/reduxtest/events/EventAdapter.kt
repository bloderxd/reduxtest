package com.vitorprado.reduxtest.events

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.vitorprado.reduxtest.R

class EventAdapter(val context: Context, val events: List<String>, val onClickListener: (String) -> Any?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder?.itemView?.findViewById(R.id.name) as TextView).text = events[position]
        holder.itemView.setOnClickListener { onClickListener(events[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder = MyViewHolder(context)

    override fun getItemCount(): Int = events.size

    class MyViewHolder(context: Context) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.event_item, null, false))
}