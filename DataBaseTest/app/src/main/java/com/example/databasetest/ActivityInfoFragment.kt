package com.example.databasetest

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import com.example.databasetest.adapters.RecyclerViewAdapter
import com.example.databasetest.dummy.DummyContent

/**
 * A fragment representing a list of Items.
 */
class ActivityInfoFragment(val supplier: RecyclerViewAdapter.RecyclerViewSupplier) : Fragment(),TimerActivity.onTimertickListner{

    private var columnCount = 1
    private lateinit var tickListener: TimerActivity.onTimertickListner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_activity_info_list, container, false)

        // Set the adapter
        val ra=RecyclerViewAdapter(supplier,resources.getStringArray(R.array.activity_type).toList())
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter =ra

            }
            tickListener=ra
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(supplier: RecyclerViewAdapter.RecyclerViewSupplier) =
                ActivityInfoFragment(supplier)
    }

    override fun onTick(pos: Int) {
        tickListener.onTick(pos)
    }

    override fun onFinish(pos: Int) {
       tickListener.onFinish(pos)
    }

    override fun onBegin(pos: Int) {
        tickListener.onBegin(pos)
    }

    override fun onNext(pos: Int) {
        tickListener.onNext(pos)
    }

    override fun onSkip(pos: Int) {
        tickListener.onSkip(pos)
    }
}