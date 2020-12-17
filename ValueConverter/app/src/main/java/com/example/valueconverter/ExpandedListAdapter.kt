package com.example.valueconverter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView


open class ExpandedListAdapter(
    private val context:Context,
    private val titleList:List<String>,
    private val dataList:HashMap<String,List<String>>
):BaseExpandableListAdapter() {
    override fun getGroup(p0: Int): Any =titleList[p0]

    override fun isChildSelectable(p0: Int, p1: Int): Boolean =true
    override fun hasStableIds(): Boolean =false

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        var convert=p2
        val listTitle=getGroup(p0) as String
        if(convert==null){
            val layoutInflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convert=layoutInflater.inflate(R.layout.list_item,null)
        }
        val listTitleTextView=convert!!.findViewById<TextView>(R.id.listView)
        listTitleTextView.text=listTitle
        listTitleTextView.textSize= 25.0F
        listTitleTextView.setTextColor(Color.BLACK)
        return convert
    }

    override fun getChildrenCount(p0: Int): Int {
        return dataList[titleList[p0]]!!.size
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return dataList[titleList[p0]]!![p1]
    }
    override fun getGroupId(p0: Int): Long =p0.toLong()
    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {

        var convert=p3
        val listTitle=getChild(p0,p1) as String
        if(convert==null){
            val layoutInflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convert=layoutInflater.inflate(R.layout.list_item,null)
        }
        val listTitleTextView=convert!!.findViewById<TextView>(R.id.listView)
        listTitleTextView.text=listTitle
        listTitleTextView.setTextColor(Color.GRAY)
        return convert
    }

    override fun getChildId(p0: Int, p1: Int): Long = p1.toLong()

    override fun getGroupCount(): Int =titleList.size
}