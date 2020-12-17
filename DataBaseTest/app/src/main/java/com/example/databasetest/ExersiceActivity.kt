package com.example.databasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databasetest.adapters.ExersiceListAdapter

class ExersiceActivity : AppCompatActivity(),ExersiceListAdapter.IExercixeAdapterListner {
    lateinit var recycleadapter:ExersiceListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exersice)
        recycleadapter= ExersiceListAdapter(application,this,resources.getStringArray(R.array.activity_type).toList())
        val exeList=findViewById<RecyclerView>(R.id.exe_list)
        with(exeList){
            layoutManager=LinearLayoutManager(context)
            adapter=recycleadapter
        }
    }

    override fun onExerciseChoose(exeId: Int) {
        val intent= Intent()
        intent.putExtra("exeId",exeId)
        setResult(0,intent)
        finish()
    }
}