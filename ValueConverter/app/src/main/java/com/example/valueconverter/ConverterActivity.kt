package com.example.valueconverter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.RequiresApi
import android.view.DisplayCutout
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.Paid.activity_converter.*
import kotlinx.android.synthetic.main.activity_converter.*
import kotlinx.android.synthetic.main.activity_converter.down_view
import kotlinx.android.synthetic.main.activity_converter.sourcespin
import kotlinx.android.synthetic.main.activity_converter.targetspin
import kotlinx.android.synthetic.main.activity_converter.up_view

open class ConverterActivity : AppCompatActivity() {
    val ConverterMap= mapOf(
        "Distance" to {->

            types=resources.getStringArray(R.array.distance)
            converter=DistanceConverter()
        },
        "Weight" to {->

            types=resources.getStringArray(R.array.weight)
            converter=WeightConverter()
        },
        "Temperature" to {->

            types=resources.getStringArray(R.array.temperature)
            converter=TemperaturConverter()
        }
        )
    var types= arrayOf<String>()
    val fragment_tag:String="NUMPAD"


    var converter:ValueConverter=DistanceConverter()

    var sourcescale=""
    var targetscale=""

    private var clipboard: ClipboardManager? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        val fragmentManager=supportFragmentManager

        val type=intent.getStringExtra("type")
        ConverterMap[type]?.invoke()
        val adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,types)
        sourcespin.adapter=adapter
        targetspin.adapter=adapter
        up_view.SetTextViewClickListner(fragmentManager,fragment_tag)
        sourcespin.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                converter.SetSourceMetric(types[p2])
                sourcescale=types[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        targetspin.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                converter.SetTargetMetric(types[p2])
                targetscale=types[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun onConvertListner(view:View){
        var nmbr:Double=up_view.GetValue()
        nmbr=converter.Convert(nmbr)
        if(nmbr.toString().length>6)
            down_view.setText(nmbr.toString().subSequence(0,6))
        else
            down_view.setText(nmbr.toString())

    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun OnSwapClicked(view:View){
       // Toast.makeText(baseContext,targetscale+sourcescale,Toast.LENGTH_LONG).show()
        converter.SetTargetMetric(sourcescale)
        converter.SetSourceMetric(targetscale)
        sourcespin.setSelection(types.indexOf(targetscale))
        targetspin.setSelection(types.indexOf(sourcescale))
        var tempstring=up_view.GetValue().toString()
        up_view.SetValue(down_view.text.toString().toDouble())
        down_view.text=tempstring



    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun UpperCopyToClipboard(view:View){
        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val textToCopy = up_view.GetValue().toString()
         clipboard?.setPrimaryClip(ClipData.newPlainText("UpperValue",textToCopy))
        Toast.makeText(baseContext,"Copied to clipboard",Toast.LENGTH_SHORT).show()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("upper",up_view.GetValue().toString())
        outState.putString("lower",down_view.text.toString())
        outState.putString("target",targetscale)
        outState.putString("source",sourcescale)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        up_view.SetValue(savedInstanceState.getString("upper")!!.toDouble())
        down_view.text=savedInstanceState.getString("lower")
        targetscale= savedInstanceState.getString("target")!!
        sourcescale=savedInstanceState.getString("source")!!

    }

    fun LowerCopyToClipboard(view:View){
        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val textToCopy = down_view.text.toString()
        clipboard?.setPrimaryClip(ClipData.newPlainText("UpperValue",textToCopy))
        Toast.makeText(baseContext,"Copied to clipboard",Toast.LENGTH_SHORT).show()

    }


}