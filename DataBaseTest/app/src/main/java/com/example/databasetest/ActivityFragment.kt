package com.example.databasetest

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.databasetest.adapters.ActivityListAdapter

/**
 * A fragment representing a list of Items.
 */
class ActivityFragment(var activityClickListner:ActivityListListner) : DialogFragment() {

    private var columnCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_activity_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = ActivityListAdapter(activityClickListner, resources.getStringArray(R.array.activity_type).toList())

            }
        }
        return view
    }

    companion object {


        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(activityClickListner: ActivityListListner) =
            ActivityFragment(activityClickListner)
    }
}