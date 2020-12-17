package com.example.databasetest

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.databasetest.adapters.MainRecyclerViewAdapter
import com.example.databasetest.room.ExeActivity
import com.example.databasetest.room.Exercise

/**
 * A fragment representing a list of Items.
 */
class MainFragment : Fragment(),ActivitySubsriber {

    private var columnCount = 1
    val recycleAdapter: MainRecyclerViewAdapter = MainRecyclerViewAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_list, container, false)

        // Set the adapter
        recycleAdapter.stractivities=resources.getStringArray(R.array.activity_type).toList()
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter =recycleAdapter
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() =
                MainFragment()
    }

    override fun onNext(activity: ExeActivity) {
       recycleAdapter.onViewClicked(activity)
    }

    override fun insertValues(list: List<ExeActivity>,exercise:Exercise) {

        recycleAdapter.insertValues(list,exercise)
    }

    override fun renewItems(list: MutableList<ExeActivity>) {
        recycleAdapter.renewItems(list)
    }

    fun setCRUDListner(listner:CRUDAictivityListner)
    {
        recycleAdapter.setOnCRUDlistner(listner)
    }
}