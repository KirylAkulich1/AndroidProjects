package com.example.battleship

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.battleship.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class BFRecyclerViewAdapter()
    : RecyclerView.Adapter<BFRecyclerViewAdapter.ViewHolder>() {
    val height=10
    val width=10
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.bf_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = height*width

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       val bf_cell:ImageView=view.findViewById(R.id.bf_cell)

    }

}