package com.example.databasetest.adapters

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.databasetest.ActivityListListner
import com.example.databasetest.CRUDAictivityListner
import com.example.databasetest.R

import com.example.databasetest.dummy.DummyContent.DummyItem
import com.example.databasetest.room.ExeActivity
import com.example.databasetest.room.Exercise

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MainRecyclerViewAdapter()
    : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>(), ActivityListListner {
    var activities= mutableListOf<ExeActivity>()
    lateinit var crudListner: CRUDAictivityListner
    var exercise:Exercise?=null
    lateinit var  stractivities:List<String>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.crud_activity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = activities[position]
        //holder.image.setImageResource(item.pict)
        holder.activityName.setText(stractivities[item.title])
        holder.activityReps.setText(item.repitations.toString())
        holder.duration.text=item.duration.toString()
        holder.decrButton.setOnClickListener{
            item.repitations-=1
            holder.activityReps.setText(item.repitations.toString())
        }
        holder.increaseButton.setOnClickListener{
            item.repitations+=1
            holder.activityReps.setText(item.repitations.toString())
            crudListner.update(activities[position])
        }
        holder.delButton.setOnClickListener{
            crudListner.onDelete(activities[position])
            this.notifyItemRemoved(position)
            activities.removeAt(position)
            this.notifyItemRangeChanged(0,activities.size)

        }
        holder.decreaseDurationButton.setOnClickListener{
            if(activities[position].duration>5)
            activities[position].duration-=5
            holder.duration.text=activities[position].duration.toString()
            //this.notifyItemChanged(position)
            crudListner.update(activities[position])
        }
        holder.increaseDurationButton.setOnClickListener{
            if(activities[position].duration<65)
                activities[position].duration+=5
            holder.duration.text=activities[position].duration.toString()
            //this.notifyItemChanged(position)
            crudListner.update(activities[position])
        }
        holder.increaseRestBtn.setOnClickListener{
            if(activities[position].rest<65)
                activities[position].rest+=5
            holder.restValue.text=activities[position].rest.toString()
            crudListner.update(activities[position])
        }
        holder.decreaseRestBtn.setOnClickListener{
            if(activities[position].rest>5)
                activities[position].rest-=5
            //this.notifyItemChanged(position)
            holder.restValue.text=activities[position].rest.toString()
            crudListner.update(activities[position])
        }

        holder.linearLayout.setBackgroundColor(exercise?.color?:Color.WHITE)
    }

    override fun getItemCount(): Int = activities.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val activityName: TextView = view.findViewById(R.id.crud_activity_name)
        val activityReps:TextView=view.findViewById(R.id.rep_num)
        val decrButton:ImageButton=view.findViewById(R.id.decrease_btn)
        val increaseButton:ImageButton=view.findViewById(R.id.increase_nmbr)
        val image: ImageView =view.findViewById(R.id.crud_list_img)
        val delButton:ImageButton=view.findViewById(R.id.del_act)
        val increaseDurationButton:ImageButton=view.findViewById(R.id.increase_duration)
        val decreaseDurationButton:ImageButton=view.findViewById(R.id.decrease_duration_btn)
        val duration:TextView=view.findViewById(R.id.duration)
        val linearLayout:LinearLayout=view.findViewById(R.id.crud_activity_item)
        val increaseRestBtn:ImageButton=view.findViewById(R.id.increase_rest_nmbr)
        val decreaseRestBtn:ImageButton=view.findViewById(R.id.decrease_rest_btn)
        val restValue:TextView=view.findViewById(R.id.rest_value)
    }

    override fun onViewClicked(exercise: ExeActivity) {
        activities.add(exercise)
        this.notifyItemInserted(activities.size-1)
    }
    fun insertValues(activities:List<ExeActivity>,exercise: Exercise){
        val oldLength=this.activities.size
        this.activities.addAll(activities)
        this.exercise=exercise
        this.notifyItemRangeInserted(oldLength-1,oldLength-1+activities.size)
    }

    fun setOnCRUDlistner(listner:CRUDAictivityListner)
    {
        crudListner=listner
    }
    fun renewItems(list:MutableList<ExeActivity>){
        activities=list
        notifyItemRangeChanged(0,activities.size)

    }


}