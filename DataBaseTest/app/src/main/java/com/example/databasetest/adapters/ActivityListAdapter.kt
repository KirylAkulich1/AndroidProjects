package com.example.databasetest.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.databasetest.ActivityListListner
import com.example.databasetest.R

import com.example.databasetest.dummy.DummyContent.DummyItem
import com.example.databasetest.room.ExeActivity

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ActivityListAdapter(val activityListListner: ActivityListListner,val activities:List<String>): RecyclerView.Adapter<ActivityListAdapter.ViewHolder>() {

    private val imgs= listOf(R.drawable.ic_baseline_accessibility_24, R.drawable.ic_baseline_accessibility_new_24, R.drawable.ic_baseline_accessible_forward_24,
            R.drawable.ic_sharp_accessible_24, R.drawable.ic_sharp_accessible_24)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_activity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.activityName.setText(activities[position])
        holder.image.setImageResource(imgs[position])
        holder.activityName.setOnClickListener{
            activityListListner.onViewClicked(ExeActivity(position,"descr",10,1,0,0,
            imgs[position],0))
        }

    }

    override fun getItemCount(): Int = activities.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val activityName: TextView = view.findViewById(R.id.activity_name)
        val image:ImageView=view.findViewById(R.id.list_img)
    }
}