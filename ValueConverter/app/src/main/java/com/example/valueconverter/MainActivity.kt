package com.example.valueconverter

import android.support.v7.app.AppCompatActivity
import android.view.View;
import android.widget.ExpandableListView;
import android.os.Bundle
import com.example.valueconverter.MainActivity.ExpandableListData.data
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent

class MainActivity : AppCompatActivity() {
    internal object ExpandableListData {
        val data: HashMap<String, List<String>>
            get() {
                val expandableListDetail =
                    HashMap<String, List<String>>()
                val Physical: MutableList<String> = ArrayList()
                Physical.add("Distance")
                val temperature: MutableList<String> = ArrayList()
                temperature.add("Temperature")
                val weight: MutableList<String> = ArrayList()
                weight.add("Weight")
                expandableListDetail["Physical"] =Physical
                expandableListDetail["Weight"] = weight
                expandableListDetail["Engineering"] = temperature
                return expandableListDetail
            }

    }
    val groupList:List<String>
    get(){
        return listOf<String>("Physical","Engineering","Weight")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val extended_list=findViewById<ExpandableListView>(R.id.main_extended)
        extended_list.setAdapter(ExpandedListAdapter(this,
        ArrayList(groupList), data))
        extended_list!!.setOnChildClickListener{
            _,_,groupPosition:Int,childPosition:Int,id:Long->
            var intent=Intent(this,ConverterActivity::class.java)
            intent.putExtra("type", data[groupList[groupPosition]]!![childPosition])
            startActivity(intent)
            false
        }
    }
}