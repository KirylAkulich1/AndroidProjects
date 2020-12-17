package com.example.databasetest.adapters

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.databasetest.R
import com.example.databasetest.room.Exercise
import com.example.databasetest.room.ExerciseRepository

class ExersiceListAdapter(val application:Application,var listner:IExercixeAdapterListner,val str_activities:List<String>) :RecyclerView.Adapter<ExersiceListAdapter.ViewHolder>() {
    val exersiceRepository:ExerciseRepository
    val exersiceList:MutableList<Exercise>
    init {
        exersiceRepository= ExerciseRepository(application)
        exersiceList= exersiceRepository.getExersiceList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.exersice_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = exersiceList[position]
        holder.exeName.text=item.name
        val activities=exersiceRepository.getRelatedActivitiesN(exersiceList[position].id!!,3)
        if(activities.size>0 )
            holder.fstAct.text=str_activities[activities[0].title]
        if(activities.size>1)
            holder.sndAct.text=str_activities[activities[1].title]
        if(activities.size>2)
            holder.thAct.text=str_activities[activities[2].title]
        holder.chooseBtn.setOnClickListener{
            listner.onExerciseChoose(item.id!!)
        }
        holder.del.setOnClickListener{
            exersiceRepository.removeExercise(exersiceList[position])
            notifyItemRemoved(position)
            exersiceList.removeAt(position)

        }
        holder.linearLayout.setBackgroundColor(item.color)

    }

    override fun getItemCount(): Int = exersiceList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val exeName:TextView=view.findViewById(R.id.exe_name)
        val fstAct:TextView=view.findViewById(R.id.fst_act)
        val sndAct:TextView=view.findViewById(R.id.snd_act)
        val thAct:TextView=view.findViewById(R.id.th_act)
        val chooseBtn:ImageButton=view.findViewById(R.id.exercise_choose_btn)
        val del:ImageButton=view.findViewById(R.id.delete_exercixe_btn)
        val linearLayout:LinearLayout=view.findViewById(R.id.info_exe_layout)
    }
    interface IExercixeAdapterListner{
        fun onExerciseChoose(exeId:Int)
    }

}