package com.example.valueconverter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.text.Layout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_converter.view.*
import kotlinx.android.synthetic.main.sample_buttintext_view.view.*
import java.lang.StringBuilder

/**
 * TODO: document your custom view class.
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ButtintextView @JvmOverloads constructor(
    context: Context,
    attrs:AttributeSet?=null,
    defStyle:Int=0,
    defStyleRes:Int=0
):LinearLayout(context,attrs,defStyle,defStyleRes),NumPadListner{

    init{
        LayoutInflater.from(context).inflate(R.layout.sample_buttintext_view,this,true)
        decrease.setOnClickListener {
            var curr_val=integer_number.text.toString().toDouble()
            if (curr_val == null) {
                integer_number.text="0"
            }
            else{
                integer_number.text=(curr_val-1).toString()
            }

        }
        increase.setOnClickListener{
            var curr_val=integer_number.text.toString().toDouble()
            if (curr_val == null) {
                integer_number.text="0"
            }
            else{
                integer_number.text=(curr_val+1).toString()
            }
        }
    }

    fun SetTextViewClickListner(manager:FragmentManager,fragment_tag:String):Unit {
        integer_number.setOnClickListener {
            val transaction = manager.beginTransaction()
            val fragment=manager.findFragmentByTag(fragment_tag)
            if(fragment==null) {
                val numfragment=NumPadFragment()
                numfragment.setListner(this)
              //  Toast.makeText(context,integer_number.text,Toast.LENGTH_SHORT).show()
                transaction.add(R.id.details_container, numfragment, fragment_tag)

            }
            else
                transaction.remove(fragment)
            transaction.commitNow()
        }


    }

    override fun onNumberClicked(ch: Char) {
        if(integer_number.text.toString()=="0")
            integer_number.text=""
        if(integer_number.text.length>8)

            Toast.makeText(context,"Reach Presion",Toast.LENGTH_SHORT).show()
        else
            integer_number.text=integer_number.text.toString()+ch
    }

    override fun onEraseClicked() {

        if(integer_number.text.length==1)
            integer_number.text=""

        else
            integer_number.text=integer_number.text.toString().dropLast(1)
    }

    override fun onEraseAllClicked() {
       integer_number.text=""
    }

    override fun onPointClicked() {
       // Toast.makeText(context,integer_number.text,Toast.LENGTH_SHORT).show()
        if(integer_number.text.contains('.'))
            Toast.makeText(context,"Incorrect input",Toast.LENGTH_SHORT).show()
        else
        {
            integer_number.text=integer_number.text.toString()+'.'

        }
    }

    override fun onSignChangedClicked() {
        integer_number.text=(integer_number.text.toString().toDouble()*(-1.0)).toString()
    }

    fun GetValue():Double=integer_number.text.toString().toDouble()
    fun SetValue(number:Double){
        integer_number.text=number.toString()
    }

}