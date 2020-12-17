package com.example.valueconverter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_num_pad.*
import kotlinx.android.synthetic.main.fragment_num_pad.view.*
import kotlinx.android.synthetic.main.sample_buttintext_view.view.*
import java.lang.StringBuilder

class NumPadFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var numListner:NumPadListner?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_num_pad, container, false)
        view.button0?.setOnClickListener(this::onDigetClicked)
        view.button1?.setOnClickListener  (this::onDigetClicked)
        view.button2?.setOnClickListener(this::onDigetClicked)
        view.button3?.setOnClickListener  (this::onDigetClicked)
        view.button4?.setOnClickListener(this::onDigetClicked)
        view.button5?.setOnClickListener  (this::onDigetClicked)
        view.button6?.setOnClickListener(this::onDigetClicked)
        view.button7?.setOnClickListener  (this::onDigetClicked)
        view.button8?.setOnClickListener(this::onDigetClicked)
        view.button9?.setOnClickListener  (this::onDigetClicked)
        view.point?.setOnClickListener (this::onPointClicked)
        view.erbutton?.setOnClickListener (this::onEraseClicked)
        view.erallbutton1?.setOnClickListener (this::onEraseAllClicked)
        view.plusminus?.setOnClickListener(this::onSignClick)
        return view
    }

    fun setListner(viewView:ButtintextView){
       numListner=viewView

    }
    fun onDigetClicked(view:View):Unit{
        numListner?.onNumberClicked((view as Button)!!.text[0])
    }
    fun onEraseClicked(view:View):Unit{
        numListner?.onEraseClicked()
    }
    fun onEraseAllClicked(view:View):Unit{
        numListner?.onEraseAllClicked()
    }
    fun onPointClicked(view:View):Unit{
        numListner?.onPointClicked()
    }
    fun onSignClick(view:View):Unit{
        numListner?.onSignChangedClicked()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NumPadFragment().apply {

            }
    }
}